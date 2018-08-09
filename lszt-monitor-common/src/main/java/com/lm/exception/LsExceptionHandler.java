package com.lm.exception;

import com.lm.base.BaseApiService;
import com.lm.base.ResponseBase;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理控制器
 */
@ControllerAdvice
public class LsExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseBase Handler(Exception e) {
        if (e instanceof LsException) {
            //自定义异常
            LsException lsException = (LsException) e;
            return BaseApiService.setResultError(lsException.getCode(),lsException.getMsg());
        } else {
            return BaseApiService.setResultError("全局异常，未知错误");
        }
    }
}
