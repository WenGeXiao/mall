package com.wgx.ums.entity.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@ToString
public class UserMemberDTO {
    @NotBlank(message = "username can not null !")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 20,message = "密码长度在6-20之间")
    private String password;
    // 头像
    private String icon;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不合理")
    private String email;
    private String nickName;
    // 备注信息
    private String note;
}
