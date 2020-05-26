package com.ray.crm.utils;

import java.util.UUID;

/**
 * @Description: 生成UUID
 * @Author: Ray Li
 * @E-mail: ray.lir@outlook.com
 * @Date: 2020/5/26 19:38
 */
public class UUIDUtil {

    public static String getUUID(){

        return UUID.randomUUID().toString().replaceAll("-","");

    }
}
