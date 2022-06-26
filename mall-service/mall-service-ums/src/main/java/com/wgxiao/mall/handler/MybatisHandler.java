package com.wgxiao.mall.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MybatisHandler implements MetaObjectHandler {

    /**
     * 前提: 对象字段加了@TableField(fill = FieldFill.INSERT)
     * 插入sql语句事件触发，
     * 将为createTime,updateTime的时间设置为new Date();
     ******************************************
     * @author 肖文格 [2022/6/26 19:45]
     * @version 1.0.0
     ******************************************
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    /**
     *  前提: @TableField(fill = FieldFill.INSERT_UPDATE)
     *  更新sql语句触发
     *  将为updateTime的值设置为new Date();
     ******************************************
     * @author 肖文格 [2022/6/26 19:45]
     * @version 1.0.0
     ******************************************
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
