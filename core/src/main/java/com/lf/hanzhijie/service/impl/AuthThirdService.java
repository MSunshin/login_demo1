package com.lf.hanzhijie.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lf.hanzhijie.mapper.SysUserMapper;
import com.lf.hanzhijie.model.po.SysUserPO;
import com.lf.hanzhijie.model.req.UserReq;
import com.lf.hanzhijie.service.AuthService;
import com.lf.hanzhijie.service.SysUserService;
import com.lf.hanzhijie.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("Phone")
public class AuthThirdService implements AuthService {
    @Autowired
    SysUserService sysUserService;

    @Override
    public SysUserPO login(SysUserPO sysUserPO) {
        return sysUserService.phoneCode(sysUserPO);
    }

    @Override
    public String getStrategy() {
        return Constant.PHONE_CODE;
    }
}
