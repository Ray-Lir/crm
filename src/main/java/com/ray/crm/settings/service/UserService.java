package com.ray.crm.settings.service;

import com.ray.crm.settings.domain.User;

import javax.security.auth.login.LoginException;

/**
 * @Description:
 * @Author: Ray Li
 * @E-mail: ray.lir@outlook.com
 * @Date: 2020/5/26 19:17
 */
public interface UserService {
    
    /**
     * servic层账户信息核验
     *
     * @param loginAct
	 * @param loginPwd
	 * @param ip
     * @return com.ray.crm.settings.domain.User 
     */
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

}
