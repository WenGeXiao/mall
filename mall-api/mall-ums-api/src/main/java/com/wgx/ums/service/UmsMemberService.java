package com.wgx.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wgx.ums.entity.UmsMember;

public interface UmsMemberService extends IService<UmsMember> {
    void register();
}
