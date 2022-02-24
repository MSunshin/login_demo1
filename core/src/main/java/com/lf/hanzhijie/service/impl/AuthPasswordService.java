package com.lf.hanzhijie.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lf.hanzhijie.mapper.SysUserMapper;
import com.lf.hanzhijie.model.po.SysUserPO;
import com.lf.hanzhijie.model.req.UserReq;
import com.lf.hanzhijie.service.AuthService;
import com.lf.hanzhijie.service.SysUserService;
import com.lf.hanzhijie.utils.Constant;
import com.lf.hanzhijie.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("Normal0")
public class AuthPasswordService implements AuthService {
    @Autowired
    SysUserService sysUserService;

    @Override
    public SysUserPO login(SysUserPO req) {
     return sysUserService.get(req);
    }

    @Override
    public String getStrategy() {
        return Constant.NORMAL;
    }
}
