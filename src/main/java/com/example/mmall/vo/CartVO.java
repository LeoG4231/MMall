package com.example.mmall.vo;

import lombok.Data;

@Data
public class CartVO {
    private Integer id;
    private Integer quantity;
    private Float cost;
    private Integer productId;
    private String name;
    private Float price;
    private String filename;
}
