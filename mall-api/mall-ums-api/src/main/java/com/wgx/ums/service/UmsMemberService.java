package com.wgx.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wgx.result.ResponseResult;
import com.wgx.ums.entity.dto.UserMemberDTO;
import com.wgx.ums.entity.po.UmsMember;

public interface UmsMemberService extends IService<UmsMember> {
    ResponseResult register(UserMemberDTO userMemberDT);

    ResponseResult login(String username, String password);

    ResponseResult updateUser(UserMemberDTO userMemberDTO);
}
