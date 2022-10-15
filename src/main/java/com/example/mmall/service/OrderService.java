package com.example.mmall.service;

import com.example.mmall.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mmall.entity.User;
import com.example.mmall.vo.OrdersVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 杉明羽
 * @since 2022-10-02
 */
public interface OrderService extends IService<Orders> {
    public boolean save(Orders orders, User user, String address, String remark);
    public List<OrdersVO> ordersVOList(Integer id);
}
