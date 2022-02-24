package com.lf.hanzhijie.factory;

import com.lf.hanzhijie.mapper.SysUserMapper;
import com.lf.hanzhijie.service.AuthService;
import com.lf.hanzhijie.service.SysUserService;
import com.lf.hanzhijie.service.impl.AuthPasswordService;
import com.lf.hanzhijie.service.impl.AuthThirdService;
import com.lf.hanzhijie.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class LoginStrategyFactory {
    @Autowired
    Set<AuthService> typeSet;

    Map<String,AuthService> typeMap;

    public AuthService getAuth(String strategy){
        return typeMap.get(strategy);
    }

    @PostConstruct
    public void init(){
        typeMap = new HashMap<>();
        for (AuthService strategy : typeSet) {
            typeMap.put(strategy.getStrategy(),strategy);
        }
//        for (int i = 0; i < typeList.size(); i++) {
//            if (i < typeList.size()-1){
//                typeMap.put(typeList.get(i).getStrategy(),typeList.get(i));
//            }
//        }
    }
//    private final static Map<String, AuthService> LOGIN_STRATEGY_MAP = new HashMap<>();
//
//    public  final static String DEFAULT_KEY = Constant.NORMAL;
//
//    static{
//        LOGIN_STRATEGY_MAP.put(Constant.NORMAL,new AuthPasswordService());
//        LOGIN_STRATEGY_MAP.put(Constant.PHONE_CODE,new AuthThirdService());
//    }
//
//    public static AuthService getLoginStance(String key) {
//        if (LOGIN_STRATEGY_MAP.containsKey(key)){
//            return LOGIN_STRATEGY_MAP.get(key);
//        }
//        return LOGIN_STRATEGY_MAP.get(DEFAULT_KEY);
//    }

}