package com.example.mmall.controller;


import com.example.mmall.entity.User;
import com.example.mmall.service.CartService;
import com.example.mmall.service.ProductCategoryService;
import com.example.mmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 杉明羽
 * @since 2022-10-02
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;

    @GetMapping("/list/{type}/{id}")
    public ModelAndView list(@PathVariable("type") String type,
                             @PathVariable("id") Integer id,
                             HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("list",productCategoryService.getAllProductCategoryVO());
        modelAndView.addObject("productList",productService.findByCategoryId(type,id));
        User user = (User) session.getAttribute("user");
        if (user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else {
            modelAndView.addObject("cartList",cartService.finAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") Integer id,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetail");
        modelAndView.addObject("product",productService.getById(id));
        User user = (User) session.getAttribute("user");
        if (user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else {
            modelAndView.addObject("cartList",cartService.finAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }
}

