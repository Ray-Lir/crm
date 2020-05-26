package com.ray.crm.settings.dao;

import com.ray.crm.settings.domain.User;

import java.util.Map;

/**
 * @Description:
 * @Author: Ray Li
 * @E-mail: ray.lir@outlook.com
 * @Date: 2020/5/26 19:31
 */
public interface UserDao {

/**
 * dao层数据库user信息查询
 *
 * @param map
 * @return com.ray.crm.settings.domain.User 
 */
    public User login(Map<String,String> map);
}
