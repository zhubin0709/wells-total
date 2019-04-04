package com.wells.controller;

import com.google.code.kaptcha.Producer;
import com.wells.result.XyResult;
import com.wells.util.AopUtils;
import com.wells.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;
import org.apache.commons.lang3.RandomStringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zb on 2019/3/30
 */
@RestController
@RequestMapping("/getCaptchaCode")
public class CaptchaController {
    //1小时后过期
    private final static int EXPIRE = 3600 * 1;
    @Autowired
    private Producer producer;
    @Autowired
    private RedisUtil redisUtil;

    /**
     *
     *                获取验证码图片
     * @author         ccg
     * @param         request
     * @param         response
     * @return
     * @throws         IOException
     * Created        2017年1月17日 下午5:07:28
     */
    @RequestMapping("getCaptchaCode")
    public Object getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
//        response.setDateHeader("Expires", 0);
//        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        response.setHeader("Pragma", "no-cache");
//        response.setContentType("image/jpeg");
        //生成验证码文本
        String capText = producer.createText();
        System.out.println("生成验证码文本===="+capText);
        //利用生成的字符串构建图片
        BufferedImage bi = producer.createImage(capText);
        ByteArrayOutputStream  out = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", out);
        BASE64Encoder  encoder = new BASE64Encoder();
        String base64Img = encoder.encode(out.toByteArray());

        response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");

        String context = request.getContextPath();
        String token = RandomStringUtils.randomAlphanumeric(20);

        while (!StringUtils.isEmpty(redisUtil.hget("Captcha:"+token,capText))) {//redis里面如果有这个token就继续生成新的token，要生成一个没用过的token
            System.out.println("----------------------");
            System.out.println("新的token");
            token = RandomStringUtils.randomAlphanumeric(20);
        }

        redisUtil.hset("Captcha:"+token,token,capText,120);//token和验证码对应的放到redis里面
        Cookie vtokenCookie = new Cookie("vtoken", token);//把token放入到页面里去
        vtokenCookie.setMaxAge(60 * 30);
        vtokenCookie.setPath(context);
        response.addCookie(vtokenCookie);
        return base64Img;
    }
}
