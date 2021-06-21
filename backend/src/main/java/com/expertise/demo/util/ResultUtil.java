package com.expertise.demo.util;


import com.expertise.demo.common.Result;

public class ResultUtil {

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setData(data);
        result.setCode(0);
        result.setMessage("success");
        return result;
    }

    public static Result fail(String message) {
        Result result = new Result();
        result.setCode(-1);
        result.setMessage(message);
        return result;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("success");
        return result;
    }
}
