package com.example.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mmall.entity.Cart;
import com.example.mmall.entity.User;
import com.example.mmall.service.CartService;
import com.example.mmall.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 杉明羽
 * @since 2022-10-02
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserAddressService userAddressService;

    @GetMapping("/add/{productId}/{price}/{quantity}")
    public String addCart(@PathVariable("productId") Integer productId,
                            @PathVariable("price") Float price,
                            @PathVariable("quantity") Integer quantity,
                            HttpSession session){
        Cart cart = new Cart();
        cart.setProductid(productId);
        cart.setQuantity(quantity);
        cart.setCost(quantity*price);
        User user = (User) session.getAttribute("user");
        cart.setUserid(user.getId());
        try {
            if (cartService.save(cart)){
                return "redirect:/cart/findAllCart";
            }
        } catch (Exception e){
            return "redirect:/productCategory/list";
        }
        return null;
    }

    @GetMapping("/findAllCart")
    public ModelAndView findAllCart(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement1");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("cartList",cartService.finAllCartVOByUserId(user.getId()));
        return modelAndView;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        cartService.removeById(id);
        return "redirect:/cart/findAllCart";
    }

    @GetMapping("/settlement2")
    public ModelAndView settlement2(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement2");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("cartList",cartService.finAllCartVOByUserId(user.getId()));
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userid",user.getId());
        modelAndView.addObject("addressList",userAddressService.list(wrapper));
        return modelAndView;
    }

    @PostMapping("/update/{id}/{quantity}/{cost}")
    @ResponseBody
    public String update(@PathVariable("id") Integer id,
                         @PathVariable("quantity") Integer quantity,
                         @PathVariable("cost") Float cost){
        Cart cart = cartService.getById(id);
        cart.setQuantity(quantity);
        cart.setCost(cost);
        if (cartService.updateById(cart)){
            return "success";
        }else {
            return "fail";
        }
    }


}

