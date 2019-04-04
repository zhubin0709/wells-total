package com.wells.Interceptor;

import com.wells.result.XyResult;
import com.wells.util.JsonUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by zb on 2019/3/13
 */
public class LoginInfoExceptionInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object shiroLoginFailure=  request.getAttribute("shiroLoginFailure");
        Object shiroAuthenticationFailure=  request.getAttribute("shiroAuthenticationFailure");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        if(shiroLoginFailure != null||shiroAuthenticationFailure != null) {
            PrintWriter pw = response.getWriter();
            StringBuffer error=new StringBuffer();
            if (shiroLoginFailure != null) {
               // pw.write(JsonUtils.objectToJson(XyResult.build(500, shiroLoginFailure.toString())));
                error.append(shiroLoginFailure.toString());
            }
            if (shiroAuthenticationFailure != null) {
               // pw.write(JsonUtils.objectToJson(XyResult.build(500, shiroAuthenticationFailure.toString())));
                error.append(shiroAuthenticationFailure.toString());
            }
            pw.write(JsonUtils.objectToJson(XyResult.build(500,error.toString())));
            pw.flush();
            pw.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
