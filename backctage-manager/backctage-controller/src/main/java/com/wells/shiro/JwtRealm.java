package com.wells.shiro;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wells.pojo.SysUser;
import com.wells.service.backctage.custom.ResourceService;
import com.wells.util.JsonUtils;
import com.wells.util.JwtUtils;
import io.jsonwebtoken.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zb on 2019/2/21
 */
public class JwtRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return "JwtRealm";
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Reference
    private ResourceService resourceService;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*********************************************
         * RestfulPermissionFilter 过滤器说明：
         * Actions representing HTTP Method values (GET -> read, POST -> create, etc)
         * private static final String CREATE_ACTION = "create";
         * private static final String READ_ACTION = "read";
         * private static final String UPDATE_ACTION = "update";
         * private static final String DELETE_ACTION = "delete";
         *********************************************/
        // 如果不为securityManager配置缓存管理器，该方法在每次鉴权前都会从数据库中查询权限数据。
        // 分布式环境下，建议将权限保存在redis中，避免每次从数据库中加载。
        Claims claims = jwtUtils.parseJWT(principals.toString());
        SysUser user= JsonUtils.jsonToPojo(claims.getSubject(),SysUser.class);
        // 得到用户的权限code
        List<String> permissionCodeList = resourceService.listPermissionCodeByUserId(user.getUserId());
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        for (String permissionCode : permissionCodeList) {
            if (permissionCode != null && !permissionCode.trim().equals("")) {
                simpleAuthorInfo.addStringPermission(permissionCode);
            }
            // 如果要添加基于角色的鉴权，可调用simpleAuthorInfo.addRole("role_name")添加用户所属角色。
        }
        return simpleAuthorInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String jwt = (String) jwtToken.getPrincipal();
        try {
            Claims claims = jwtUtils.parseJWT(jwt);
           SysUser user= JsonUtils.jsonToPojo(claims.getSubject(),SysUser.class);
            if(!jwtToken.getHost().equals(user.getLoginIp())) {
                throw new AuthenticationException("令牌来路非法");
            }
            return new SimpleAuthenticationInfo(jwt, Boolean.TRUE, this.getName());
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("令牌过期:" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("令牌无效:" + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("令牌格式错误:" + e.getMessage());
        } catch (SignatureException e) {
            throw new AuthenticationException("令牌签名无效:" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("令牌参数异常:" + e.getMessage());
        } catch (Exception e) {
            throw new AuthenticationException("令牌错误:" + e.getMessage());
        }
    }
}
