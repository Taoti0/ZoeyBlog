package com.zoey.blog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Zoey He
 * @data: 2022/4/3  13:59
 * CREATE BY IDEA
 * ZoeyBlog: AdminLoginInterceptor.java
 *
 * AdminLoginInterceptor: 登陆拦截器，若用户登陆 session 过期，需重新登陆
 */
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestServletPath =request.getServletPath();        // 获取请求路径
        // 若当前请求路径来自 /admin, 判断 session 用户信息是否已过期或没有此 session
        if (requestServletPath.startsWith("/admin") && request.getSession().getAttribute("loginUser") == null){
            request.getSession().setAttribute("errorMsg", "身份过期，请重新登陆");
            response.sendRedirect(request.getContextPath() + "/admin/login");       // 跳转到登陆界面
            return false;
        }else{
            request.getSession().removeAttribute("errorMsg");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
