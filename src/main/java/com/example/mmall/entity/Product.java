package com.example.mmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 杉明羽
 * @since 2022-10-02
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class Product implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 名称
     */
      private String name;

      /**
     * 描述
     */
      private String description;

      /**
     * 价格
     */
      private Float price;

      /**
     * 库存
     */
      private Integer stock;

      /**
     * 分类1
     */
      private Integer categoryleveloneid;

      /**
     * 分类2
     */
      private Integer categoryleveltwoid;

      /**
     * 分类3
     */
      private Integer categorylevelthreeid;

      /**
     * 文件名称
     */
      private String filename;


}
