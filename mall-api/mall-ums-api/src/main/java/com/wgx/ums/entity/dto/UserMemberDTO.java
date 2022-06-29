package com.wgx.ums.entity.dto;

import lombok.Data;

@Data
public class UserMemberDTO {
    private String username;
    private String password;
    // 头像
    private String icon;
    private String email;
    private String nickName;
    // 备注信息
    private String note;
}
