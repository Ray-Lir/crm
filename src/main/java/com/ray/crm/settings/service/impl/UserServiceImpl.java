package com.ray.crm.settings.service.impl;

import com.ray.crm.settings.dao.UserDao;
import com.ray.crm.settings.domain.User;
import com.ray.crm.settings.service.UserService;
import com.ray.crm.utils.Const;
import com.ray.crm.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Ray Li
 * @E-mail: ray.lir@outlook.com
 * @Date: 2020/5/26 19:18
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    /**
     * 方法简介 service层进行账户信息核验
     *
     * @param loginAct
	 * @param loginPwd
	 * @param ip
     * @return com.ray.crm.settings.domain.User
     * @version 1.0.0
     * @author Ray Li
     * @date 2020/5/26 19:54
     */
    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        Map<String,String> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);

        //验证账号密码是否正确，通过dao层执行sql语句来验证，返回User
        User user = userDao.login(map);

        //user==null说明账号密码不正确
        if(user==null){

            throw new LoginException("账号密码错误");

        }

        //验证失效时间
        String expireTime = user.getExpireTime();
        if(expireTime.compareTo(DateTimeUtil.getSysTime())<0){

            throw new LoginException("账号已失效");

        }

        //验证锁定状态
        String lockState = user.getLockState();
        if(lockState!=null){

            if(Const.LOCKSTATE_CLOSE.equals(lockState)){

                throw new LoginException("账号已锁定");

            }

        }

        //验证ip地址
        String allowIps = user.getAllowIps();
        if(allowIps!=null){

            if(!allowIps.contains(ip)){

                throw new LoginException("ip地址受限");

            }

        }

        //验证通过，返回user
        return user;
    }
}
