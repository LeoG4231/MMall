package com.example.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mmall.entity.Product;
import com.example.mmall.entity.ProductCategory;
import com.example.mmall.mapper.ProductCategoryMapper;
import com.example.mmall.mapper.ProductMapper;
import com.example.mmall.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mmall.vo.ProductVO;
import com.example.mmall.vo.ProjectCategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 杉明羽
 * @since 2022-10-02
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProjectCategoryVO> getAllProductCategoryVO() {
        //一级分类
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type",1);
        List<ProductCategory> levelOne = productCategoryMapper.selectList(queryWrapper);
        List<ProjectCategoryVO> levelOneVO = levelOne.stream().map(e -> new ProjectCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
        //图片赋值
        //商品信息赋值
        for (int i = 0; i < levelOneVO.size(); i++) {
            levelOneVO.get(i).setBannerImg("/images/banner"+i+".png");
            levelOneVO.get(i).setTopImg("/images/top"+i+".png");
            queryWrapper = new QueryWrapper();
            queryWrapper.eq("categoryleveloneid",levelOneVO.get(i).getId());
            List<Product> productList =  productMapper.selectList(queryWrapper);
            List<ProductVO> productVOList = productList.stream()
                    .map(e -> new ProductVO(
                            e.getId(),
                            e.getName(),
                            e.getPrice(),
                            e.getFilename()
                    )).collect(Collectors.toList());
            levelOneVO.get(i).setProductVOList(productVOList);
        }
        for(ProjectCategoryVO levelOneProjectCategoryVO:levelOneVO){
            //二级分类
            queryWrapper = new QueryWrapper();
            queryWrapper.eq("type",2);
            queryWrapper.eq("parentid",levelOneProjectCategoryVO.getId());
            List<ProductCategory> levelTwo = productCategoryMapper.selectList(queryWrapper);
            List<ProjectCategoryVO> levelTwoVO = levelTwo.stream().map(e -> new ProjectCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
            levelOneProjectCategoryVO.setChildren(levelTwoVO);
            for (ProjectCategoryVO levelTwoProjectCategoryVO:levelTwoVO){
                //三级分类
                queryWrapper = new QueryWrapper();
                queryWrapper.eq("type",3);
                queryWrapper.eq("parentid",levelTwoProjectCategoryVO.getId());
                List<ProductCategory> levelThree= productCategoryMapper.selectList(queryWrapper);
                List<ProjectCategoryVO> levelThreeVO = levelThree.stream().map(e -> new ProjectCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
                levelTwoProjectCategoryVO.setChildren(levelThreeVO);
            }
        }
        return levelOneVO;
    }
}
