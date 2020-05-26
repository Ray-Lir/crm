package com.ray.crm.interceptor;

import com.ray.crm.exception.InterceptorException;
import com.ray.crm.settings.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 登录拦截
 * @Author: Ray Li
 * @E-mail: ray.lir@outlook.com
 * @Date: 2020/5/26 20:02
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*
            通过session中是否有user对象来判断登录状态
            如果登录，则返回true（将请求放行）
            如果没有登录过，则回到登录页

         */

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {

            //说明没有登录过
            throw new InterceptorException();

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
