package com.lf.hanzhijie.service;

import com.lf.hanzhijie.model.po.SysUserPO;
import com.lf.hanzhijie.model.req.UserReq;

public interface SysUserService {
    String sendSms(String phone);

    SysUserPO phoneCode(SysUserPO sysUserPO);

    SysUserPO get(SysUserPO req);
}
