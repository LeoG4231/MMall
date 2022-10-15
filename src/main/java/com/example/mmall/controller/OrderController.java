package com.example.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mmall.entity.Orders;
import com.example.mmall.entity.User;
import com.example.mmall.service.CartService;
import com.example.mmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 杉明羽
 * @since 2022-10-02
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @PostMapping("/settlement3")
    public ModelAndView settlement3(Orders orders, HttpSession session,String address,String remark){
        User user = (User) session.getAttribute("user");
        orderService.save(orders,user,address,remark);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement3");
        modelAndView.addObject("cartList",cartService.finAllCartVOByUserId(user.getId()));
        return modelAndView;
    }
}

