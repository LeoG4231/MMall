package com.example.mmall.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProjectCategoryVO {
    private Integer id;
    private String name;
    private List<ProjectCategoryVO> children;
    private String bannerImg;
    private String topImg;
    private List<ProductVO> productVOList;

    public ProjectCategoryVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
