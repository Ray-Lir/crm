package com.ray.crm.settings.web.controller;

import com.ray.crm.settings.domain.User;
import com.ray.crm.settings.service.UserService;
import com.ray.crm.utils.HandleFlag;
import com.ray.crm.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.login.LoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: 系统设置控制层
 * @Author: Ray Li
 * @E-mail: ray.lir@outlook.com
 * @Date: 2020/5/26 19:17
 */
@Controller
@RequestMapping("/settings/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *  获取cookie并登录
     *
     * @param loginAct
     * @param loginPwd
     * @param flag
     * @param request
     * @param response
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @version 1.0.0
     * @author Ray Li
     * @date 2020/5/26 21:59
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public Map<String, Object> login(String loginAct, String loginPwd, String flag, HttpServletRequest request, HttpServletResponse response) throws LoginException {


        String ip = request.getRemoteAddr();

        loginPwd = MD5Util.getMD5(loginPwd);

        User user = userService.login(loginAct, loginPwd, ip);

        request.getSession().setAttribute("user", user);

        //通过flag值是否为a，来判断是否需要处理"十天免登陆"操作
        //使用cookie来对"十天免登陆"功能进行操作

        if ("a".equals(flag)) {

            Cookie cookie1 = new Cookie("loginAct", loginAct);
            Cookie cookie2 = new Cookie("loginPwd", loginPwd);

            cookie1.setMaxAge(60 * 60 * 24 * 10);
            cookie2.setMaxAge(60 * 60 * 24 * 10);

            cookie1.setPath("/");
            cookie2.setPath("/");

            response.addCookie(cookie1);
            response.addCookie(cookie2);

        }

        return HandleFlag.successTrue();

    }


    /**
     *  检测是否保存cookie并跳转至登录页
     *
     * @param request
     * @return java.lang.String
     * @version 1.0.0
     * @author Ray Li
     * @date 2020/5/26 19:32
     */
    @RequestMapping("/toLogin.do")
    public String toLogin(HttpServletRequest request) {

        //验证用户是否执行过了十天免登陆操作
        Cookie[] cookies = request.getCookies();

        String loginAct = null;
        String loginPwd = null;

        if (cookies != null && cookies.length > 0) {

            for (Cookie cookie : cookies) {

                String name = cookie.getName();
                if ("loginAct".equals(name)) {

                    loginAct = cookie.getValue();

                    continue;

                }

                if ("loginPwd".equals(name)) {

                    loginPwd = cookie.getValue();

                }


            }

        }

        if (loginAct != null && loginPwd != null) {

            //说明已经做过十天免登陆了
            //通过账号密码重新做登录操作

            String ip = request.getRemoteAddr();

            try {

                User user = userService.login(loginAct, loginPwd, ip);

                request.getSession().setAttribute("user", user);

                //以上我们是在判断了十天免登陆操作后，自动的为用户做了登录操作
                //需要直接跳转到欢迎页
                return "redirect:/workbench/toIndex.do";

            } catch (LoginException e) {
                e.printStackTrace();

                return "/login";

            }


        } else {

            //说明并没有从cookie中取得我们所需要的账号和密码
            //没有做过十天免登陆
            //访问登录页
            return "/login";

        }

    }

    /**
     * 退出登录
     *
     * @param session
	 * @param response
     * @return java.lang.String
     * @version 1.0.0
     * @author Ray Li
     * @date 2020/5/26 23:35
     */
    @RequestMapping("/logout.do")
    public String logout(HttpSession session, HttpServletResponse response) {

        //将session对象销毁
        session.invalidate();

        /**

            Cookie类型没有提供直接销毁的方法
            可以使用新的cookie来代替老的cookie方法将老的cookie对象进行销毁的操作
            新cookie将保存时间设置为0，将相当于是cookie的销毁操作了

         */
        Cookie cookie1 = new Cookie("loginAct", null);
        Cookie cookie2 = new Cookie("loginPwd", null);
        cookie1.setPath("/");
        cookie2.setPath("/");
        cookie1.setMaxAge(0);
        cookie2.setMaxAge(0);

        response.addCookie(cookie1);
        response.addCookie(cookie2);

        return "/login";

    }


}
