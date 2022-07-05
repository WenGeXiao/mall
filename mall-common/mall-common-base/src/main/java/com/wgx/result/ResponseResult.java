package com.wgx.result;

import com.wgx.enums.StateCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor // 一定要加，不然builder报错
public class ResponseResult<T> {

    private int code;
    private String msg;
    private T data;

    // 返回成功
    public static ResponseResult.ResponseResultBuilder getSuccessBuilder(){
        return ResponseResult.builder().code(StateCodeEnum.SUCCESS.getCode()).msg(StateCodeEnum.SUCCESS.getMsg()).data(null);
    }

    // 返回失败
    public static ResponseResult.ResponseResultBuilder getFAILBuilder(){
        return ResponseResult.builder().code(StateCodeEnum.ERROR.getCode()).msg(StateCodeEnum.ERROR.getMsg()).data(null);
    }

}
