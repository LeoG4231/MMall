package com.example.mmall.service;

import com.example.mmall.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mmall.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 杉明羽
 * @since 2022-10-02
 */
public interface CartService extends IService<Cart> {
    public List<CartVO> finAllCartVOByUserId(Integer id);
}
