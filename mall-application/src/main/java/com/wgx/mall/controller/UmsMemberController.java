package com.wgx.mall.controller;

import com.wgx.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理系统controller
 ******************************************
 * @author 肖文格 [2022/6/26 20:33]
 * @version 1.0.0
 ******************************************
 */
@RestController
@RequestMapping("/ums")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsService;

    @RequestMapping("/register")
    public String register(){

        return "hello";
    }
}

