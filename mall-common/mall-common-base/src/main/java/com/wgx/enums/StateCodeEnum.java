package com.wgx.enums;

/**
 *  错误码定义标准： 100-200用户
 *                200-299 商品
 */
public enum StateCodeEnum {

    SUCCESS(200, "success"),
    ERROR(500, "error"),
    /************** 系统**************/
    ENCODING_ERROR(505, "编码异常"),
    SIGN_ERROR(506, "签名错误"),
   /**************** 用户 **************/
    USER_NAME_REPEAT(100, "用户名重复"),
    USER_NAME_AND_PWD_CAN_NOT_NULL(101, "username or password can not null !"),
    USER_NAME_OR_PWD_ERROR(102, "username or password error!"),
    TOKEN_ERROR(103, "token error or null");


    private int  code;
    private String msg;

    // 枚举构造函数不允许public修饰符
    StateCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取 code
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置 code
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取 msg
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置 msg
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
