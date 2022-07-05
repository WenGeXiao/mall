package com.wgx.mall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wgx.enums.StateCodeEnum;
import com.wgx.mall.mapper.UmsMemberMapper;
import com.wgx.mall.util.JwtUtil;
import com.wgx.result.ResponseResult;
import com.wgx.ums.entity.dto.UserMemberDTO;
import com.wgx.ums.entity.po.UmsMember;
import com.wgx.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author wgx
 * @since 2022-06-26
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseResult register(UserMemberDTO userMemberDTO) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(userMemberDTO, umsMember);
        // 业务校验
        if (!StringUtils.isEmpty(bussinessValidate(umsMember))) {
            return ResponseResult.builder().code(StateCodeEnum.USER_NAME_REPEAT.getCode()).
                    msg(StateCodeEnum.USER_NAME_REPEAT.getMsg()).data(null).build();
        }
        // 密码加密
        encryPwd(umsMember);
        // todo 异常处理
        // mybatis_plus返回的是主键id
        umsMemberMapper.insert(umsMember);
        return ResponseResult.builder().code(StateCodeEnum.SUCCESS.getCode()).
                msg("用户注册成功").data(null).build();
    }

    private void encryPwd(UmsMember umsMember) {
        // 取出密码
        String pwd = umsMember.getPassword();
        // 加密
        umsMember.setPassword(bCryptPasswordEncoder.encode(pwd));
    }

    private String bussinessValidate(UmsMember umsMember) {
        if (!isRepeatUserName(umsMember.getUsername())) {
            return "用户名重复";
        }
        return null;
    }


    /**
     * 校验用户名是否重复
     * 1.查询判断
     * 2.数据库 username + 唯一索引
     * *****************************************
     * @param username 用户名
     * @return true:不重复 false 重复
     * @author 肖文格 [2022/6/29 23:56]
     * @version 1.0.0
     * *****************************************
     */
    private boolean isRepeatUserName(String username) {
        // 防止用户名重复
        // 获取用户名
        // 根据用户名去查询数据库,如果有返回，则返回用户名重复，否则继续返回true
        QueryWrapper<UmsMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        int count = umsMemberMapper.selectCount(queryWrapper);
        return count == 0;
    }

    @Override
    public ResponseResult login(String username, String password) {
        // 根据用户名去查询数据库，如果有没有返回，提示报错
        QueryWrapper<UmsMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UmsMember umsMember = umsMemberMapper.selectOne(queryWrapper);
        if (StringUtils.isEmpty(umsMember)) {
            return ResponseResult.builder().code(StateCodeEnum.USER_NAME_OR_PWD_ERROR.getCode())
                    .msg(StateCodeEnum.USER_NAME_OR_PWD_ERROR.getMsg()).build();
        } else if (bCryptPasswordEncoder.matches(password, umsMember.getPassword())) {
            // 否则就是密码(方法自动把前端密码加密) 与 数据库的密码进行对比
            // 下发jwt token
            return ResponseResult.builder().code(StateCodeEnum.SUCCESS.getCode()).
                    msg("login success").data(JwtUtil.createToken(username)).build();
        } else {
            return ResponseResult.builder().code(StateCodeEnum.USER_NAME_OR_PWD_ERROR.getCode())
                    .msg(StateCodeEnum.USER_NAME_OR_PWD_ERROR.getMsg()).build();
        }
    }
}
