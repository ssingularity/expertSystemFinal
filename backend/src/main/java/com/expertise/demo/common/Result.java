package com.expertise.demo.common;

import lombok.Data;

@Data
public class Result<T> {
    T data;

    String message;

    Integer code;
}
