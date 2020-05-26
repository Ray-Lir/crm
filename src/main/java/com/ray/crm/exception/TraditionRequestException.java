package com.ray.crm.exception;

/**
 * @Description: 传统异常
 * @Author: Ray Li
 * @E-mail: ray.lir@outlook.com
 * @Date: 2020/5/26 19:22
 */
public class TraditionRequestException extends Exception {


    public TraditionRequestException(){}

    public TraditionRequestException(String msg){

        super(msg);

    }
}
