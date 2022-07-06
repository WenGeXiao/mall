package com.wgx.mall.controller;

import com.wgx.annotation.TokenCheck;
import com.wgx.result.ResponseResult;
import com.wgx.ums.entity.dto.UserMemberDTO;
import com.wgx.ums.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("/registerUser")
    public ResponseResult registerUser(@RequestBody @Validated UserMemberDTO userMemberDTO){
        return umsService.register(userMemberDTO);
    }

    @RequestMapping("/login")
    public ResponseResult login(@RequestBody UserMemberDTO userMemberDTO){
       return umsService.login(userMemberDTO.getUsername(), userMemberDTO.getPassword());
    }

    @TokenCheck
    @PostMapping("/updateUser")
    public ResponseResult updateUser(@RequestBody UserMemberDTO userMemberDTO){
        return umsService.updateUser(userMemberDTO);
    }
}

