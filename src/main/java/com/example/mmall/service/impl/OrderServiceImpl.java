package com.example.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mmall.entity.*;
import com.example.mmall.mapper.*;
import com.example.mmall.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mmall.vo.OrderDetailVO;
import com.example.mmall.vo.OrdersVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 杉明羽
 * @since 2022-10-02
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean save(Orders orders, User user ,String address ,String remark) {
        //判断是新地址还是老地址
        if (orders.getUseraddress().equals("newAddress")){
            //存入数据库
            UserAddress userAddress = new UserAddress();
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddress.setIsdefault(1);
            userAddress.setUserid(user.getId());

            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("isdefault",1);
            UserAddress oldDefault = userAddressMapper.selectOne(wrapper);
            oldDefault.setIsdefault(0);
            userAddressMapper.updateById(oldDefault);
            userAddressMapper.insert(userAddress);
            orders.setUseraddress(address);
        }
        //存储orders
        orders.setUserid(user.getId());
        orders.setLoginname(user.getLoginname());
        String serialNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for (int i=0;i<32;i++){
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            serialNumber = result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setSerialnumber(serialNumber);
        orderMapper.insert(orders);

        //存储orderDetail
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userid",user.getId());
        List<Cart> cartList = cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart,orderDetail);
            orderDetail.setId(null);
            orderDetail.setOrderid(orders.getId());
            orderDetailMapper.insert(orderDetail);
        }

        //清空购物车
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("userid",user.getId());
        cartMapper.delete(wrapper1);
        return true;
    }

    @Override
    public List<OrdersVO> ordersVOList(Integer id) {
        List<OrdersVO> ordersVOList = new ArrayList<>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userid",id);
        List<Orders> ordersList = orderMapper.selectList(wrapper);
        for (Orders orders : ordersList) {
            OrdersVO ordersVO = new OrdersVO();
            List<OrderDetailVO> orderDetailVOList = new ArrayList<>();
            QueryWrapper wrapper1 = new QueryWrapper();
            wrapper1.eq("orderid",orders.getId());
            List<OrderDetail> orderDetail = orderDetailMapper.selectList(wrapper1);
            for (OrderDetail detail : orderDetail) {
                OrderDetailVO orderDetailVO = new OrderDetailVO();
                QueryWrapper wrapper2 = new QueryWrapper();
                wrapper2.eq("id",detail.getProductid());
                Product product = productMapper.selectOne(wrapper2);
                BeanUtils.copyProperties(detail,orderDetailVO);
                orderDetailVO.setName(product.getName());
                orderDetailVO.setFilename(product.getFilename());
                orderDetailVO.setPrice(product.getPrice());
                orderDetailVOList.add(orderDetailVO);
            }
            BeanUtils.copyProperties(orders,ordersVO);
            ordersVO.setOrderDetailVOList(orderDetailVOList);
            ordersVOList.add(ordersVO);
        }
        return ordersVOList;
    }
}
