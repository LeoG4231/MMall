package com.example.mmall.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrdersVO {
    private Integer id;
    private Integer userid;
    private String loginname;
    private String serialnumber;
    private String useraddress;
    private Float cost;
    private List<OrderDetailVO> orderDetailVOList;
}
