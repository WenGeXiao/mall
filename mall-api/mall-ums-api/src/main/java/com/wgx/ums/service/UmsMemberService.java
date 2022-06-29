package com.wgx.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wgx.ums.entity.dto.UserMemberDTO;
import com.wgx.ums.entity.po.UmsMember;

public interface UmsMemberService extends IService<UmsMember> {
    String register(UserMemberDTO userMemberDT);
}
