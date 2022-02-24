package com.lf.hanzhijie.controller;

import com.lf.hanzhijie.factory.LoginStrategyFactory;
import com.lf.hanzhijie.model.po.Result;
import com.lf.hanzhijie.model.po.SysUserPO;
import com.lf.hanzhijie.model.req.UserReq;
import com.lf.hanzhijie.service.AuthService;
import com.lf.hanzhijie.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    LoginStrategyFactory loginStrategyFactory;

    /**
     * TODO
     * 1、设计模式修改 调用类
     * 2、判断
     * 3、冗余代码
     *
     * 验证码五分钟之内发三次
     * redis抽出
     * 配置文件提取mysql,redis
     *
     *
     *
     */
    @PostMapping("/login")
    public Result<SysUserPO> login(@RequestBody SysUserPO sysUserPO) {
//        if (ObjectUtil.equal("PHONE_PAS", sysUserPO.getLoginType()) || ObjectUtil.equal("EMAIL_PAS",sysUserPO.getLoginType())) {
//            return Result.success(sysUserService.get(sysUserPO));
//        } else if (ObjectUtil.equal("PHONE_CODE",sysUserPO.getLoginType())){
//            return Result.success(sysUserService.phoneCode(sysUserPO));
//        }
//        return Result.error("不存在该类型登陆");
        return Result.success(loginStrategyFactory.getAuth(sysUserPO.getLoginType()).login(sysUserPO));
    }

//    @PostMapping("/login2")
//    public Result<SysUserPO> login(@RequestBody UserReq req) {
//        if (ObjectUtil.equal("PHONE_PAS", req.getLoginType()) || ObjectUtil.equal("EMAIL_PAS",req.getLoginType())) {
//            return Result.success(sysUserService.get(req));
//        }else if (ObjectUtil.equal("PHONE_CODE",req.getLoginType())){
//            return Result.success(sysUserService.PhoneCode(req.getPhoneNum(),req.getCaptcha()));
//        }
//        return Result.error("用户不存在");
//    }

    @GetMapping("/login/send")
    public Result<String> send(@RequestParam("phone") String phone){
        return Result.success(sysUserService.sendSms(phone));
    }

//    private Result<SysUserPO> getUserPOResult(SysUserPO sysUserPO) {
//        if (ObjectUtil.isNotEmpty(sysUserPO.getEmail())) {
//            SysUserPO userPO = sysUserService.get(sysUserPO);
//            if (ObjectUtil.isNotEmpty(userPO)) {
//                if (ObjectUtil.equal(sysUserPO.getEmail(), userPO.getEmail())) {
//                    return Result.success(userPO);
//                }
//            }
//        }else {
//            throw new RuntimeException("email不能为空");
//        }
//        return null;
//    }
//
//    private Result<SysUserPO> getSysUserPOResult(SysUserPO sysUserPO) {
//        if (ObjectUtil.isNotEmpty(sysUserPO.getPhoneNum())) {
//            SysUserPO userPO = sysUserService.get(sysUserPO);
//            if (ObjectUtil.isNotEmpty(userPO)) {
//                if (ObjectUtil.equal(sysUserPO.getPassword(), userPO.getPassword())) {
//                    return Result.success(userPO);
//                }
//            }
//        }else {
//            throw new RuntimeException("手机号不能为空");
//        }
//        return null;
//    }
}
