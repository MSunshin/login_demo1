package com.lf.hanzhijie.service;

import com.lf.hanzhijie.model.po.SysUserPO;
import com.lf.hanzhijie.model.req.UserReq;

public interface AuthService {
    SysUserPO login(SysUserPO req);

    String getStrategy();
}
