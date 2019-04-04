package com.wells.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zb on 2019/2/21
 */
public class RestfulPermissionFilter extends AuthorizationFilter {

    public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroAuthenticationFailure";
    private String failureKeyAttribute = DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;
    public String getFailureKeyAttribute() {
        return failureKeyAttribute;
    }
    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String method = ((HttpServletRequest) request).getMethod();

        // 处理跨域请求
        if (request instanceof ShiroHttpServletRequest) {
            if (StringUtils.equalsIgnoreCase("OPTIONS", method)) {
                return true;
            }
        }

        String permissionString = ((String[]) mappedValue)[0];
        Subject subject = getSubject(request, response);
        switch (method.toLowerCase()) {
            case "get":
                permissionString += ":read";
                break;
            case "put":
                permissionString += ":update";
                break;
            case "post":
                permissionString += ":create";
                break;
            case "delete":
                permissionString += ":delete";
                break;
        }
            boolean is=subject.isPermitted(permissionString);
        if(!is){
            onAuthenticationFailure(request);
        }
        return true;
    }
    protected boolean onAuthenticationFailure(ServletRequest request) {
        setFailureAttribute(request);
        return true;
    }
    protected void setFailureAttribute(ServletRequest request) {
        request.setAttribute(getFailureKeyAttribute(),"you haven't authentication");
    }
}
