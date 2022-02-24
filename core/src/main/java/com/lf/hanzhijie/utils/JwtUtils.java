package com.lf.hanzhijie.utils;


import cn.hutool.core.util.ObjectUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author lenovo
 */
public class JwtUtils {
    /**
     * 设置Token有效时间为1小时
     */
    private static final int TOKEN_TIME_OUT = 1 * 3600;
    /**
     * 设置加密key为 livefuller
     */
    private static final String TOKEN_SECRET = "livefuller";

    /**
     * 生成token
     *
     * @param params
     * @return
     */
    public static String getToken(Map params) {
        long currentTime = System.currentTimeMillis();
        try {
            return Jwts.builder()
                    //加密方式
                    .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(TOKEN_SECRET.getBytes("UTF-8")))
                    //设置过期时间
                    .setExpiration(new Date(currentTime + TOKEN_TIME_OUT * 1000))
                    .addClaims(params).compact();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Token中的claims信息
     */
    public static Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encode(TOKEN_SECRET.getBytes("UTF-8")))
                    .parseClaimsJws(token).getBody();
        } catch (UnsupportedEncodingException e) {


            e.printStackTrace();
            return null;
        }
    }

    /**
     * 是否有效 true-有效，false-失效
     */
    public static boolean verifyToken(String token) {
        if (ObjectUtil.isEmpty(token)) {
            return false;
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encode(TOKEN_SECRET.getBytes("UTF-8")))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}