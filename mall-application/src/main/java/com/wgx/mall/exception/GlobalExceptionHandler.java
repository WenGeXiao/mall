package com.wgx.mall.exception;

import com.wgx.enums.StateCodeEnum;
import com.wgx.mall.exception.define.TokenException;
import com.wgx.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult exceptionResult(Exception e) {
        log.info("异常信息=========" + e.getMessage());
        return ResponseResult.builder().code(StateCodeEnum.ERROR.getCode()).msg("系统通用异常").
                build();
    }

    @ExceptionHandler(TokenException.class)
    @ResponseBody
    public ResponseResult tokenExceptionResult(Exception e) {
        log.info("异常信息=========" + e.getMessage());
        return ResponseResult.builder().code(StateCodeEnum.TOKEN_ERROR.getCode()).
                msg(StateCodeEnum.TOKEN_ERROR.getMsg()).
                build();
    }

}
