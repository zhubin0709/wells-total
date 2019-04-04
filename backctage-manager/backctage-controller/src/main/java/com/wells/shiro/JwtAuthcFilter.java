package com.wells.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zb on 2019/2/21
 */
public class JwtAuthcFilter extends AccessControlFilter {

    public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";
    private String failureKeyAttribute = DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;
    public String getFailureKeyAttribute() {
        return failureKeyAttribute;
    }
    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //处理跨域请求
        if(request instanceof ShiroHttpServletRequest) {
            if(StringUtils.equalsIgnoreCase("OPTIONS", ((ShiroHttpServletRequest) request).getMethod())) {
                return true;
            }
        }
        // 拦截后先进入该方法。直接返回false，交由onAccessDenied处理鉴权与登录逻辑
        return false;
    }
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
        //从header中得到token
        String token = ((HttpServletRequest)request).getHeader("X-Token");
        String host = request.getRemoteHost();

        JwtToken jwtToken = new JwtToken(token, host);
        try {
            //委托给Realm进行登录
           getSubject(request, response).login(jwtToken);
        } catch (Exception ex) {
            onLoginFailure(jwtToken,ex,request,response);

        }
        return true;
    }
    protected boolean onLoginFailure(AuthenticationToken token, Exception e,
                                     ServletRequest request, ServletResponse response) {
        setFailureAttribute(request, e);
        return true;
    }
    protected void setFailureAttribute(ServletRequest request, Exception ae) {
        request.setAttribute(getFailureKeyAttribute(), ae.getMessage());
    }
}
