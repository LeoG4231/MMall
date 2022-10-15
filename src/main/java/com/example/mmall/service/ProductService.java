package com.example.mmall.service;

import com.example.mmall.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 杉明羽
 * @since 2022-10-02
 */
public interface ProductService extends IService<Product> {
    public List<Product> findByCategoryId(String type,Integer id);
}
