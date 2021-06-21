package com.expertise.demo.config;

import com.expertise.demo.common.Result;
import com.expertise.demo.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     */
    @ExceptionHandler()
    @ResponseBody
    ResponseEntity<Result<Object>> handle(RuntimeException e) {
        log.error("", e);
        Result<Object> result = ResultUtil.fail(e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
