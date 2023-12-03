package com.group.study.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;
import java.util.List;

public class JwtUtils {

    /**
     * jwt密钥
     */
    private static final Algorithm SECRET = Algorithm.HMAC256("gp_study_secret");

    /**
     * 生成一个用户认证token
     *
     * @param data 需要token携带的鉴权数据(例如用户id等)
     * @return token字符串
     */
    public static String createToken(String... data) {
        return JWT.create()
                .withAudience(data)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(SECRET);
    }

    /**
     * 获取token中携带的鉴权信息
     *
     * @param token 目标token
     * @return token中携带的用户鉴权信息
     */
    public static List<String> getTokenAud(String token) {
        return JWT.decode(token).getAudience();
    }

    /**
     * 校验token是否有效
     *
     * @param token 需要验证的token
     * @return true为有效 false为无效
     */
    public static boolean verifyToken(String token) {
        boolean status = true;
        try {
            JWT.require(SECRET).build().verify(token);
        } catch (JWTVerificationException e) {
            status = false;
        }
        return status;
    }
}
