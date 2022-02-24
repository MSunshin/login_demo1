package com.lf.hanzhijie.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lf.hanzhijie.config.RedisConfig;
import com.lf.hanzhijie.mapper.SysUserMapper;
import com.lf.hanzhijie.model.po.SysUserPO;
import com.lf.hanzhijie.model.req.UserReq;
import com.lf.hanzhijie.service.SysUserService;
import com.lf.hanzhijie.utils.Constant;
import com.lf.hanzhijie.utils.JwtUtils;
import com.lf.hanzhijie.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserPO> implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    RedisCache redisCache;

    @Override
    public String sendSms(String phone) {
        if (ObjectUtil.isEmpty(phone)) {
            throw new RuntimeException("手机号不能为空");
        }
        String code = "1234";
//        Long count =redisTemplate.opsForValue().increment(Constant.CHECK_CODE_ + phone + "_" + code, 1);
        if (ObjectUtil.isNull(redisCache.get(Constant.CHECK_CODE_+phone))){
            redisCache.setEx(Constant.CHECK_CODE_+phone,code,5,TimeUnit.MINUTES);
        }
        Long count = redisCache.incr(Constant.CHECK_CODE_+phone+"_"+code,1l);
        if (count > 3) {
            throw new RuntimeException("您的操作过于频繁");
        }
            redisCache.expire(Constant.CHECK_CODE_+phone,5,TimeUnit.MINUTES);
        return code;
    }

    @Override
    public SysUserPO phoneCode(SysUserPO sysUserPO) {
        SysUserPO po;
        if (ObjectUtil.notEqual(sysUserPO.getCaptcha(), redisCache.get(Constant.CHECK_CODE_ + sysUserPO.getPhoneNum()))) {
            throw new RuntimeException("验证码错误");
        }
        po = get(sysUserPO);
        return po;
    }

    @Override
    public SysUserPO get(SysUserPO req) {
        QueryWrapper<SysUserPO> query = new QueryWrapper<>();
        query.eq("phone_num", req.getPhoneNum()).or().eq("email", req.getEmail());
        SysUserPO userPO = Assert.notNull(sysUserMapper.selectOne(query), "用户数据为空");
        if (ObjectUtil.notEqual(req.getPassword(), userPO.getPassword()) && ObjectUtil.equal(Constant.NORMAL,req.getLoginType())) {
            throw new RuntimeException("密码错误");
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", req.getId());
        userPO.setTokenId(JwtUtils.getToken(result));
        return userPO;
    }
}
