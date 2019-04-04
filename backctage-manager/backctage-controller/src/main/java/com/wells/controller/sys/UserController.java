package com.wells.controller.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wells.controller.BaseController;
import com.wells.pojo.SysUser;
import com.wells.result.XyResult;
import com.wells.service.backctage.SysUserService;
import com.wells.util.JwtUtils;
import com.wells.util.RedisUtil;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zb on 2019/2/15
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Reference
    private SysUserService sysUserService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisUtil redisUtil;


    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestBody SysUser vo, @RequestHeader(value="X-Token") String token) {
        String currentUserId = this.getSubjectFromJwt(jwtUtils, token, "userId");

        SysUser dto = new SysUser();
        dto.setUserName(vo.getUserName());
        dto.setLoginName(vo.getLoginName());
        dto.setUserId(Integer.parseInt(currentUserId));

        // 随机生成salt
        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = secureRandomNumberGenerator.nextBytes().toHex();
        Md5Hash md5 = new Md5Hash(vo.getPassword(), salt, 10);
        // 设置盐
        dto.setSalt(salt);
        // 设置新密码
        String md5Password = md5.toHex();
        dto.setPassword(md5Password);

        SysUser user = sysUserService.saveUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vo);
    }

    @GetMapping("/{id}")
    public XyResult getUserById(@PathVariable("id") int id) {
        SysUser user = sysUserService.selectUserByUserId(id);
        if (user != null) {
            user.setPassword("");
            SysUser vo = new SysUser();
            BeanUtils.copyProperties(user, vo);
            redisUtil.set(user.getUserId().toString(),vo);
            System.out.println("***************"+redisUtil.get(user.getUserId().toString()));
            return XyResult.ok(vo);
        }
        return XyResult.ok("没有");
    }
}
