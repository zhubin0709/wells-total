package com.wells.shiro;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * Created by zb on 2019/2/15
 */
public class Test {
//    public static void main(String[] args){
 //       String hashAlgorithmName = "MD5";
//        String credentials = "123456";
//        int hashIterations = 10;
//        ByteSource credentialsSalt = ByteSource.Util.bytes("2");
//        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
//        System.out.println(obj);
//    }
private static String id="jwt";

    private static String secret="6686df7fc3a34e26a61c034d5ec82488";

    /**
     * 存活时间（毫秒）
     */
    private static long ttlMillis=3600000;

    public Test(String id, String secret, long ttlMillis) {
        this.id = id;
        this.secret = secret;
        this.ttlMillis = ttlMillis;
    }

    /**
     * 生成加密key
     *
     * @return
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(secret);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     *
     * @param subject
     * @return
     */
    public static String createJWT(String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).claim("host","192.108.149").signWith(signatureAlgorithm,
                key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        SecretKey key = generalKey();
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        return claims;
    }

    public static void main(String[] args) {
//        Claims jwt=Test.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1NTIwMTI2MTAsInN1YiI6ImFkbWluIiwiaG9zdCI6IjE5Mi4xMDguMTQ5IiwiZXhwIjoxNTUyMDE2MjEwfQ._rdwvRscSCTz6Hq4XxB-q928F8XOAh3Lfx3uC59h7GE");
//        System.out.print(jwt);
//        System.out.print(jwt.get("host"));
//        System.out.print(jwt.getSubject());
        //连接本地的 Redis 服务
//        Jedis jedis = new Jedis("localhost");
//        System.out.println("连接成功");
//        //设置 redis 字符串数据
//        // 获取存储的数据并输出
//        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
            ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("/spring/applicationContext-redis.xml");
             RedisTemplate<String, Object> redisTemplate = appCtx.getBean("redisTemplate", RedisTemplate.class);
            //添加一个 key
            ValueOperations<String, Object> value = redisTemplate.opsForValue();
            value.set("lp", "hello word");
            //获取 这个 key 的值
            System.out.println(value.get("lp"));
    }
}
