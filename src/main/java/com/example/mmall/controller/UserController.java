package com.example.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mmall.entity.User;
import com.example.mmall.service.CartService;
import com.example.mmall.service.OrderService;
import com.example.mmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String register(User user, Model model){
        boolean result = false;
        try {
            result = userService.save(user);
        } catch (Exception e) {
            model.addAttribute("error",user.getLoginname()+"已存在!");
            return "register";
        }
        if (result) return "login";
        return "register";
    }

    /**
     * 登录
     * @param loginName
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("loginname",loginName);
        wrapper.eq("password",password);
        User user = userService.getOne(wrapper);
        if (user == null){
            return "login";
        }else {
            session.setAttribute("user",user);
            return "redirect:/productCategory/list";
        }
    }

    /**
     * 退出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    /**
     * 用户信息
     */

    @GetMapping("/userInfo")
    public ModelAndView userInfo(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userInfo");
        modelAndView.addObject("cartList",cartService.finAllCartVOByUserId(user.getId()));
        return modelAndView;
    }

    @GetMapping("/orderList")
    public ModelAndView orderList(HttpSession session){
        User user = (User) session.getAttribute("user");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userid",user.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        modelAndView.addObject("cartList",cartService.finAllCartVOByUserId(user.getId()));
        modelAndView.addObject("order",orderService.ordersVOList(user.getId()));
        return modelAndView;
    }
}

