package com.example.mmall.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class UserAddress implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户主键
     */
      private Integer userid;

      /**
     * 地址
     */
      private String address;

      /**
     * 创建时间
     */
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createtime;

      /**
     * 是否是默认地址（1:是 0否）
     */
      private Integer isdefault;

      /**
     * 备注
     */
      private String remark;


}
