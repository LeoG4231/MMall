package com.example.mmall.vo;

import lombok.Data;

@Data
public class ProductVO {
    private Integer id;
    private String name;
    private Float price;
    private String filename;

    public ProductVO(Integer id, String name, Float price, String filename) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.filename = filename;
    }
}
