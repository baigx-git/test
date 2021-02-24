package com.luxondata.test.component.exception;


import com.luxondata.test.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(TestException.class)
    public <T> Result<T> handle(HttpServletRequest request, TestException exception){
        Result<T> objectResult = new Result<>();
        objectResult.setMessage(exception.getMessage());
        objectResult.setCode(1);
        return objectResult;
    }

    /**
     * AuthenticationException
     *
     */
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public <T> Result<T> exceptionHandler(AuthenticationException e) {
        Result<T> objectResult = new Result<>();
        objectResult.setMessage(e.getLocalizedMessage() == null ? e.getLocalizedMessage() :
                "用户认证错误或已过期");
        objectResult.setCode(500);
        return objectResult;
    }

    /**
     * 处理其他异常
     *
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public <T> Result<T> exceptionHandler(Exception e) {
        Result<T> objectResult = new Result<>();
        objectResult.setMessage(e.getMessage());
        objectResult.setCode(500);
        return objectResult;
    }
}
