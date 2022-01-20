package com.msy.phonestore.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.CartAndUserAndPhoneAndPhoneDetailetDTO;
import com.msy.phonestore.dto.OrderAndUserDTO;
import com.msy.phonestore.dto.OrderDetailetAndPhoneAndPhoneDetailetAndComboAndAssureDTO;
import com.msy.phonestore.mapper.*;
import com.msy.phonestore.pojo.*;
import com.msy.phonestore.service.ifc.IOrderService;
import com.msy.phonestore.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/07/16:41
 * @Description:
 */
@Service
public class OrderServiceImpl implements IOrderService {



    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailetMapper orderDetailetMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserCouponMapper userCouponMapper;
    @Autowired
    private IntegralMapper integralMapper;
    @Autowired
    private OrderTimeMapper orderTimeMapper;
    @Autowired
    private OrderAddressMapper orderAddressMapper;
    @Autowired
    private ParcelMapper parcelMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Transactional
    @Override
    public ResponseModel findOrdersAndOrderDetailet(Map<String, Object> map) throws Exception {

        List<Orders> orders = orderMapper.queryMsgByMap(map);
        return ResponseModel.success(ResCode.SUCCESS,orders);
    }

    @Transactional
    @Override
    public ResponseModel insertOrder(Map<String,Object> map) throws Exception {

        Orders order = JSON.parseObject(JSON.toJSONString(map.get("order")), Orders.class);
        String  orderId = SnowflakeIdFactory.getOrderNo();
        order.setOrderId(orderId);
        order.setSubmitOrderTime(new Date());
        int row = orderMapper.insert(order);

        //设置订单有效时间
        redisTemplate.opsForValue().set("orderId"+orderId,orderId,30, TimeUnit.MINUTES);

        //插入进订单地址
        OrderAddress orderAddress=order.getOrderAddress();
        orderAddress.setOrderId(orderId);
        int row2 = orderAddressMapper.insert(orderAddress);
        if(row2==0){
            return ResponseModel.fail(ResCode.FAIL);
        }

        //订单状态及时间
        OrderTime orderTime=new OrderTime();
        orderTime.setOrderId(orderId);
        orderTime.setOrderTime(new Date());
        orderTime.setOrderTimeContent("你的订单已提交,等待系统确认!");
        int row3 = orderTimeMapper.insert(orderTime);
        if(row3==0){
            return ResponseModel.fail(ResCode.FAIL);
        }

        //把地址设为未选中
        Address address=new Address();
        address.setAddressId(order.getAddressId());
        address.setState(0);
        int row4 = addressMapper.updateById(address);

        //使用的优惠券
            if(order.getUserCouponId()!=0){
                UserCoupon userCoupon = userCouponMapper.selectOne(new QueryWrapper<UserCoupon>()
                        .eq("userCouponId", order.getUserCouponId())
                        .eq("userId", order.getUserId()));
                if(userCoupon!=null){
                    if(userCoupon.getUserCouponCount()-1!=-1){
                        userCoupon.setUserCouponCount(userCoupon.getUserCouponCount()-1);
                        int row5 = userCouponMapper.updateById(userCoupon);
                        if(row5==0){
                            return ResponseModel.fail(ResCode.FAIL);
                        }
                    }
                }
            }

            //使用积分
        if(order.getPointsOffer()!=null){
            Map<String,Object> map1=new HashMap<>();
            map1.put("userId",order.getUserId());
            map1.put("pointsOffer", order.getPointsOffer());
            int row6 = integralMapper.updateIntegralMsg(map1);
            if(row6==0){
                return ResponseModel.fail(ResCode.FAIL);
            }
        }

        //插入订单明细表
        int row1=0;
        List<CartAndUserAndPhoneAndPhoneDetailetDTO> dtos= order.getCartDTOList();
        for(CartAndUserAndPhoneAndPhoneDetailetDTO dto1:dtos){
            OrderDetailet orderDetailet=new OrderDetailet();
            orderDetailet.setOrderId(orderId);
            orderDetailet.setAssureId(dto1.getAssureId());
            orderDetailet.setComboId(dto1.getComboId());
            orderDetailet.setPhoneDetailetId(dto1.getPhoneDetailetId());
            orderDetailet.setPhoneCount(dto1.getQuantity());
            orderDetailet.setAssureCount(1);

            int row7 = orderDetailetMapper.insert(orderDetailet);
            if(row7>0){
                row1++;
            }
        }
        if(row>0 && row1>0 && row4>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);

    }

    @Override
    public ResponseModel updateOrder(Orders order) throws Exception {
        int row = orderMapper.updateById(order);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel deleteOrder(Object[] ids) throws Exception {
        int row=0;
        int row1=0;
        int row2=0;
        for(int i=0;i<ids.length;i++){
            row += orderMapper.delete(new QueryWrapper<Orders>().eq("orderId",ids[i]));
            row1 += orderTimeMapper.delete(new QueryWrapper<OrderTime>().eq("orderId", ids[i]));
            row2 +=orderAddressMapper.delete(new QueryWrapper<OrderAddress>().eq("orderId", ids[i]));
        }
        if(row>0 && row1>0 && row2>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseModel paymentMsg(Map<String,Object> map) throws Exception {
        //支付更改订单状态
        Orders order = JSON.parseObject(JSON.toJSONString(map.get("order")), Orders.class);
        order.setPaymentTime(new Date());
        int row = orderMapper.updateById(order);
        redisTemplate.delete("orderId"+order.getOrderId());

        //插入支付时间
        OrderTime orderTime=JSON.parseObject(JSON.toJSONString(map.get("orderTime")), OrderTime.class);
        orderTime.setOrderTime(new Date());
        int row1 = orderTimeMapper.insert(orderTime);

        if(row>0 && row1>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseModel confirmReceiptGoods(Map<String, Object> map) throws Exception {
        Orders order = JSON.parseObject(JSON.toJSONString(map.get("order")), Orders.class);
        int row = orderMapper.updateById(order);

        OrderTime orderTime=JSON.parseObject(JSON.toJSONString(map.get("orderTime")), OrderTime.class);
        orderTime.setOrderTime(new Date());
        int row1 = orderTimeMapper.insert(orderTime);

        Integral integral=JSON.parseObject(JSON.toJSONString(map.get("integral")), Integral.class);
        int row2 = integralMapper.updateIntegral(integral);
        if(row>0 && row1>0 && row2>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseModel cancelOrderMsg(Map<String, Object> map) throws Exception {

        Orders order = JSON.parseObject(JSON.toJSONString(map.get("order")), Orders.class);
        redisTemplate.delete("orderId"+order.getOrderId());
        //取消订单更改订单状态
        int row = orderMapper.updateById(order);

        UserCoupon userCoupon = JSON.parseObject(JSON.toJSONString(map.get("userCoupon")), UserCoupon.class);
        //退回优惠券
        userCoupon.setUserCouponCount(1);
        int row1 = userCouponMapper.updateById(userCoupon);

        OrderTime orderTime=JSON.parseObject(JSON.toJSONString(map.get("orderTime")), OrderTime.class);
        orderTime.setOrderTime(new Date());
        //插入时间状态
        int row2 = orderTimeMapper.insert(orderTime);

        if(row>0 && row1>0 && row2>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel findOrderListPageMsg(Map<String, Object> map) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<Orders>()
                .selectAll(Orders.class)
                .select(Users::getName,Users::getGender,Users::getBirthday,Users::getPhoneNumber,Users::getUserImg,Users::getUserName)
                .innerJoin(Users.class,Users::getUserId,Orders::getUserId)
                .eq(map.get("orderState")!=null,Orders::getOrderState,map.get("orderState"));
        Page orderPage=new Page<>((Integer)map.get("pageNumber"),(Integer)map.get("pageSize"));
        IPage<OrderAndUserDTO> orderAndUserDTOIPage = orderMapper.selectJoinPage(orderPage, OrderAndUserDTO.class, mpjLambdaWrapper);
        return ResponseModel.success(ResCode.SUCCESS,orderAndUserDTOIPage);
    }

    @Override
    @Transactional
    public ResponseModel confirmDeliveryMsg(Map<String, Object> map) throws Exception {

        Orders order = JSON.parseObject(JSON.toJSONString(map.get("order")), Orders.class);
        int row = orderMapper.updateById(order);
        OrderTime orderTime=JSON.parseObject(JSON.toJSONString(map.get("orderTime")), OrderTime.class);
        orderTime.setOrderTime(new Date());
        int row1 = orderTimeMapper.insert(orderTime);

        Parcel parcel = JSON.parseObject(JSON.toJSONString(map.get("parcel")), Parcel.class);
        parcel.setDeliveryTime(new Date());
        int row2 = parcelMapper.insert(parcel);

        if(row>0 && row1>0 && row2>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

}
