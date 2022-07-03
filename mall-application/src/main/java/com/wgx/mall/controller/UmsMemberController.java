package com.wgx.mall.controller;

import com.wgx.ums.entity.dto.UserMemberDTO;
import com.wgx.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String register(@RequestBody UserMemberDTO userMemberDTO){
        return umsService.register(userMemberDTO);
    }

    @RequestMapping("/login")
    public String login(@RequestBody UserMemberDTO userMemberDTO){
       return umsService.login(userMemberDTO.getUsername(), userMemberDTO.getPassword());
    }
}

