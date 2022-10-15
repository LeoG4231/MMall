package com.example.mmall.service;

import com.example.mmall.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mmall.vo.ProjectCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 杉明羽
 * @since 2022-10-02
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProjectCategoryVO> getAllProductCategoryVO();
}
