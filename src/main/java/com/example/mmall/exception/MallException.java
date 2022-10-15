package com.example.mmall.exception;

import com.example.mmall.enums.ResultEnum;

public class MallException extends RuntimeException{
    public MallException(String error) {
        super(error);
    }
    public MallException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
}
