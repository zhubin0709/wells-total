package com.wells.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wells.annotation.RedisCacheGet;
import com.wells.pojo.SysLoginLog;
import com.wells.pojo.SysUser;
import com.wells.result.XyResult;
import com.wells.service.backctage.SysLoginLogService;
import com.wells.service.backctage.SysUserService;
import com.wells.service.backctage.custom.ResourceService;
import com.wells.util.JsonUtils;
import com.wells.util.JwtUtils;
import com.wells.util.RedisUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by zb on 2019/2/22
 */
@RestController
@RequestMapping()
public class loginController {

    @Reference
    private SysUserService sysUserService;
    @Reference
    private ResourceService routerService;
    @Reference
    private SysLoginLogService sysLoginLogService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public XyResult login(HttpServletRequest request,String loginName,String password){
        if(StringUtils.isEmpty(loginName)||StringUtils.isEmpty(password)){
            return XyResult.build(500,"用户名或密码不能为空！");
        }
        SysUser user = sysUserService.selectUserByLoginName(loginName);
        if(user == null) {
            return XyResult.build(500,"用户名不存在");
        }
        if(!"0".equals(user.getStatus())) {
            return XyResult.build(500,"该账户被锁定");
        }

        return null;
    }

    @PostMapping("/token")
    @RedisCacheGet(key = "SysUser:#{loginName}",field="#{loginName}",expire = 60,fieldExpire = true)
    public XyResult postToken(HttpServletRequest request, String loginName, String password,String captcha) {
        if(StringUtils.isEmpty(loginName)||StringUtils.isEmpty(password)){
            return XyResult.build(500,"用户名或密码不能为空！");
        }
        SysUser user = sysUserService.selectUserByLoginName(loginName);
        if(user == null) {
            return XyResult.build(500,"用户名不存在");
        }
        if("1".equals(user.getStatus())) {
            return XyResult.build(500,"该账户被锁定");
        }
        Md5Hash md5 = new Md5Hash(password, user.getSalt(), 10);
        String md5Password = md5.toHex();
        if(!md5Password.equals(user.getPassword())) {
            return XyResult.build(500,"登录失败，用户名或密码错误！");
            }
        //如果已经登录过了，redis中就会存在该token
         if(!StringUtils.isEmpty(redisUtil.get(user.getEmail())))  {
             return XyResult.build(500,"您已经登录，token未失效！");
         }
        SysUser sysUser=saveUserInfoToRedis(request,user,600);
        return XyResult.ok(sysUser);
    }
    private SysUser saveUserInfoToRedis(HttpServletRequest request,SysUser user,long time){
        SysUser sysUser=new SysUser();
        if (!StringUtils.isEmpty(user)){
            sysUser.setUserId(user.getUserId());
            sysUser.setUserName(user.getUserName());
            sysUser.setLoginIp(request.getRemoteHost());
            String userJson=JsonUtils.objectToJson(sysUser);
            String jwt=jwtUtils.createJWT(userJson);
            sysUser.setToken(jwt);
            //添加登录日志
            SysLoginLog sysLoginLog=new SysLoginLog();
            sysLoginLog.setLoginName(user.getLoginName());
            sysLoginLog.setIpaddr(request.getRemoteAddr());
            sysLoginLog.setLoginTime(new Date());
//            sysLoginLogService.insertLoginLog(sysLoginLog);
//            redisUtil.set(user.getEmail(),sysUser,time);
        }
        return sysUser;
    }
}
