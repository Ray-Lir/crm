package com.ray.crm.exception;

/**
 * @Description: Ajax异常
 * @Author: Ray Li
 * @E-mail: ray.lir@outlook.com
 * @Date: 2020/5/26 19:23
 */
public class AjaxRequestException extends Exception {

    public AjaxRequestException(){}

    public AjaxRequestException(String msg){

        super(msg);

    }
}
