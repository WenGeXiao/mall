package com.wgx.mall.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import java.util.Calendar;
import java.util.Date;

public class JwtUtil {
    /**
     * 密钥，仅服务端存储
     */
    private static String secret = "ko346134h_we]rg3in_yip1!";

    /**
     * @param subject   消息体
     * @param issueDate 签发时间
     * @return
     */
    public static String createToken(String subject, Date issueDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(issueDate);
        c.add(Calendar.DAY_OF_MONTH, 20);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issueDate)
                // 过期时间
                .setExpiration(c.getTime())
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
                .compact();
    }


    /**
     *  兼容redis使用，不需要手动设置过期时间
     ******************************************
     * @author 肖文格 [2022/7/5 22:36]
     * @version 1.0.0
     ******************************************
     * @param subject 主题信息
     * @return 返回jwt token
     */
    public static String createToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
                .compact();
    }


    /**
     * 解密 jwt
     * @param token
     * @return
     * @throws Exception
     */
    public static String parseToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            if (claims != null) {
                return claims.getSubject();
            }
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            System.out.println("jwt过期了");
        }
        return "";
    }

}
