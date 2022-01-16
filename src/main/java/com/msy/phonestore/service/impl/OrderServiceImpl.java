package com.msy.phonestore.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msy.phonestore.dto.CartAndUserAndPhoneAndPhoneDetailetDTO;
import com.msy.phonestore.dto.OrderDetailetAndPhoneAndPhoneDetailetAndComboAndAssureDTO;
import com.msy.phonestore.mapper.*;
import com.msy.phonestore.pojo.*;
import com.msy.phonestore.service.ifc.IOrderService;
import com.msy.phonestore.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Override
    public ResponseModel findOrdersAndOrderDetailet(Map<String, Object> map) throws Exception {
//        List<Orders> orders= orderMapper.selectByMap(map);
//        Orders order1=new Orders();
//        for(Orders orders2:orders){
//           order1 = orderMapper.selectById(orders2.getOrderId());
//            MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
//                    .selectAll(OrderDetailet.class)
//                    .select(Phone::getPname,Phone::getPhoneImg)
//                    .select(PhoneDetailet::getColor,PhoneDetailet::getPrice,PhoneDetailet::getRam,PhoneDetailet::getStorage,PhoneDetailet::getVersion)
//                    .select(PhoneCombo::getCombo,PhoneCombo::getComboPrice)
//                    .select(PhoneAssure::getAssure,PhoneAssure::getAssurePrice,PhoneAssure::getAssureImg)
//                    .innerJoin(PhoneDetailet.class,PhoneDetailet::getPDetailetId,OrderDetailet::getPhoneDetailetId)
//                    .innerJoin(Phone.class,Phone::getPhoneId,PhoneDetailet::getPhoneId)
//                    .innerJoin(PhoneCombo.class,PhoneCombo::getComboId,OrderDetailet::getComboId)
//                    .innerJoin(PhoneAssure.class,PhoneAssure::getAssureId,OrderDetailet::getAssureId)
//                    .eq(OrderDetailet::getOrderId,orders2.getOrderId());
//            List<OrderDetailetAndPhoneAndPhoneDetailetAndComboAndAssureDTO> DTOs = orderDetailetMapper.selectJoinList(OrderDetailetAndPhoneAndPhoneDetailetAndComboAndAssureDTO.class, mpjLambdaWrapper);
//            orders2.setOrderAndAllAndAssureDTOList(DTOs);
//        }

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

        //插入进订单地址
        OrderAddress orderAddress=order.getOrderAddress();
        orderAddress.setOrderId(orderId);
        int row8 = orderAddressMapper.insert(orderAddress);
        if(row8==0){
            return ResponseModel.fail(ResCode.FAIL);
        }

        //订单状态及时间
        OrderTime orderTime=new OrderTime();
        orderTime.setOrderId(orderId);
        orderTime.setOrderTime(new Date());
        orderTime.setOrderTimeContent("你的订单已提交,等待系统确认!");
        int row7 = orderTimeMapper.insert(orderTime);
        if(row7==0){
            return ResponseModel.fail(ResCode.FAIL);
        }

        //把地址设为未选中
        Address address=new Address();
        address.setAddressId(order.getAddressId());
        address.setState(0);
        int row3 = addressMapper.updateById(address);

        //使用的优惠券
            if(order.getCouponId()!=0){
                UserCoupon userCoupon = userCouponMapper.selectOne(new QueryWrapper<UserCoupon>()
                        .eq("couponId", order.getCouponId())
                        .eq("userId", order.getUserId()));
                if(userCoupon!=null && userCoupon.getUserCouponCount()-1!=0){
                    int row4 = userCouponMapper.updateUserCouponCount(userCoupon.getUserCouponId());
                    if(row4==0){
                        return ResponseModel.fail(ResCode.FAIL);
                    }
                }else {
                    int row6 = userCouponMapper.deleteById(userCoupon.getUserCouponId());
                    if(row6==0){
                        return ResponseModel.fail(ResCode.FAIL);
                    }
                }
            }

            //使用积分
        if(order.getPointsOffer()!=null){
            Map<String,Object> map1=new HashMap<>();
            map1.put("userId",order.getUserId());
            map1.put("pointsOffer", order.getPointsOffer());
            int row5 = integralMapper.updateIntegralMsg(map1);
            if(row5==0){
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

            int row2 = orderDetailetMapper.insert(orderDetailet);
            if(row2>0){
                row1++;
            }
        }
        if(row>0 && row1>0 && row3>0){
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
    public ResponseModel paymentMsg(Orders order) throws Exception {
        order.setPaymentTime(new Date());
        int row = orderMapper.updateById(order);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}
