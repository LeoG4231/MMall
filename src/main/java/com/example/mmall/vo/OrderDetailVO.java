package com.example.mmall.vo;

import lombok.Data;

@Data
public class OrderDetailVO {
    private Integer id;
    private Integer orderid;
    private Integer productid;
    private String name;
    private String filename;
    private Integer quantity;
    private Float price;
    private Float cost;
}
