package com.example.mmall.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum GenderEnum {
    WOMAN(0,"女"),
    MAN(1,"男");

    @EnumValue
    private Integer code;
    private String value;

    GenderEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
}
