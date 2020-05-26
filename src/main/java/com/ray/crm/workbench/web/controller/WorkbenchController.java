package com.ray.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 工作台控制层
 * @Author: Ray Li
 * @E-mail: ray.lir@outlook.com
 * @Date: 2020/5/26 22:15
 */
@Controller
public class WorkbenchController {

    /**
     * 方法简介 跳转至工作台首页
     *
     * @param 
     * @return java.lang.String
     * @version 1.0.0
     * @author Ray Li
     * @date 2020/5/26 22:27 
     */
    @RequestMapping("/workbench/toIndex.do")
    public String toIndex(){
        return "/workbench/index";

    }

/**
 * 方法简介  跳转至工作台主页的首页
 *
 * @param 
 * @return java.lang.String
 * @version 1.0.0
 * @author Ray Li
 * @date 2020/5/26 22:27 
 */
    @RequestMapping("/workbench/main/toIndex.do")
    public String mainToIndex(){

        return "/workbench/main/index";

    }

}
