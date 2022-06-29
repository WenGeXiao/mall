package com.wgx.mall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wgx.mall.mapper.UmsMemberMapper;
import com.wgx.ums.entity.dto.UserMemberDTO;
import com.wgx.ums.entity.po.UmsMember;
import com.wgx.ums.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String register(UserMemberDTO userMemberDTO) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(userMemberDTO, umsMember);
        // mybatis_plus返回的是主键id
        int userId = umsMemberMapper.insert(umsMember);
        return "success";
    }
}
