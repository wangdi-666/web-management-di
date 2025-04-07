package com.didi;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    /**
     * 生成JWT令牌
     */
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "ZGlkaQ==") //指定加密算法和密钥，这里的字符串使用的是didi,写入的是通过Base64加密后的结果
                .addClaims(dataMap) // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000)) //指定令牌有效期(过期时间)
                .compact(); //生成JWT令牌
        System.out.println(jwt);

    }

    /**
     * 解析JWT令牌
     */
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0NDAyMDIyMn0.xg7M9bIpR_UTNaz37cKR51XS1LEtW1zqkSC_8PeJu3k";
        Claims claims = Jwts.parser().setSigningKey("ZGlkaQ==")// 密钥需要和生成令牌时一致
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }
}
