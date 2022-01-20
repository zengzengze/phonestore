package com.msy.phonestore.vo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msy.phonestore.mapper.OrderMapper;
import com.msy.phonestore.mapper.OrderTimeMapper;
import com.msy.phonestore.mapper.UserCouponMapper;
import com.msy.phonestore.pojo.OrderTime;
import com.msy.phonestore.pojo.Orders;
import com.msy.phonestore.pojo.UserCoupon;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    public RedisKeyExpirationListener(RedisMessageListenerContainer container) {
        super(container);
    }

    @Autowired
    private OrderTimeMapper orderTimeMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserCouponMapper userCouponMapper;

    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] pattern) {
       String key=message.toString();//生效的key
        if (key!=null && key.startsWith("order")){//从失效key中筛选代表订单失效的key
            //截取订单号，查询订单，如果是未支付状态则取消订单
            String orderNo=key.substring(5);

            Orders order = orderMapper.selectOne(new QueryWrapper<Orders>()
                    .eq("orderId", orderNo));
            order.setOrderState(6);
            orderMapper.updateById(order);

            if(order.getUserCouponId()!=0){
                UserCoupon userCoupon=new UserCoupon();
                userCoupon.setUserCouponCount(1);
                userCouponMapper.updateById(userCoupon);
            }
//
            OrderTime orderTime=new OrderTime();
            orderTime.setOrderId(orderNo);
            orderTime.setOrderTime(new Date());
            orderTime.setOrderTimeContent("订单超时已自动取消!");
            orderTimeMapper.insert(orderTime);

            System.out.println("订单号为："+orderNo+"的订单超时未支付，取消订单");
//            return ResponseModel.success(ResCode.SUCCESS,orderNo);
        }
//        return ResponseModel.fail(ResCode.FAIL);
    }
}