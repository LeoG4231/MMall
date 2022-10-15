package com.example.mmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.example.mmall.enums.GenderEnum;
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
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 登录名
     */
      private String loginname;

      /**
     * 用户名
     */
      private String username;

      /**
     * 密码
     */
      private String password;

      /**
     * 性别(1:男 0：女)
     */
      private Integer sex;
//      private GenderEnum sex;

      /**
     * 身份证号
     */
      private String identitycode;

      /**
     * 邮箱
     */
      private String email;

      /**
     * 手机
     */
      private String mobile;

      /**
     * 类型（1：后台 0:前台）
     */
      private Integer type;

    private String filename;


}
