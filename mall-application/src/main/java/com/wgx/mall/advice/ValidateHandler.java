package com.wgx.mall.advice;

import com.wgx.result.ResponseResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody // 将相应内容以json格式返回，否则会404
public class ValidateHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder errorMsg = new StringBuilder();
        ex.getBindingResult().getFieldErrors().stream().anyMatch((fieldError) -> {
            errorMsg.append(fieldError.getDefaultMessage());
            // 有一个报错就退出
            return true;
        });
        return new ResponseEntity(new ResponseResult<Object>(301, errorMsg.toString(),null), HttpStatus.OK);
    }
}
