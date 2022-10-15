package com.example.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mmall.entity.Cart;
import com.example.mmall.entity.Product;
import com.example.mmall.enums.ResultEnum;
import com.example.mmall.exception.MallException;
import com.example.mmall.mapper.CartMapper;
import com.example.mmall.mapper.ProductMapper;
import com.example.mmall.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mmall.vo.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 杉明羽
 * @since 2022-10-02
 */
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean save(Cart entity) {
        //扣库存
        Product product = productMapper.selectById(entity.getProductid());
        Integer stock = product.getStock() - entity.getQuantity();
        if (stock < 0){
            log.error("【添加购物车】库存不足！stock={}",stock);
            throw new MallException(ResultEnum.STOCK_ERROR);
        }
        product.setStock(stock);
        productMapper.updateById(product);
        if (cartMapper.insert(entity) == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<CartVO> finAllCartVOByUserId(Integer id) {
        List<CartVO> cartVOList = new ArrayList<>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userid",id);
        List<Cart> cartList = cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            CartVO cartVO = new CartVO();
            Product product = productMapper.selectById(cart.getProductid());
            BeanUtils.copyProperties(product,cartVO);
            BeanUtils.copyProperties(cart,cartVO);
            cartVO.setProductId(cart.getProductid());
            cartVOList.add(cartVO);
        }
        return cartVOList;
    }
}
