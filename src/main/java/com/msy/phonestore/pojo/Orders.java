package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.msy.phonestore.dto.CartAndUserAndPhoneAndPhoneDetailetDTO;
import com.msy.phonestore.dto.OrderDetailetAndPhoneAndPhoneDetailetAndComboAndAssureDTO;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@Data
@TableName(value = "orders")
//@KeySequence(value = "SEQ_ORDERS_ORDERID")
public class Orders {

//    @TableId(value = "id",type = IdType.INPUT)
//    private Integer id;

    @TableId(value = "orderId")
    private String orderId;
    @TableField(value = "userId")
    private Integer userId;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "submitOrderTime")
    private Date submitOrderTime;

    @TableField(value = "orderTotal")
    private double orderTotal;
    @TableField(value = "addressId")
    private Integer addressId;
    @TableField(value = "orderState")
    private Integer orderState;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "paymentTime")
    private Date paymentTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "returnGoodsTime")
    private Date returnGoodsTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "confirmTime")
    private Date confirmTime;

    @TableField(value = "couponId")
    private Integer couponId;

    @TableField(value = "pointsOffer")
    private Double pointsOffer;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "shipTime")
    private Date shipTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "deliveryTime")
    private Date deliveryTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "completeTime")
    private Date completeTime;

    @TableField(exist = false)
    private List<OrderDetailet> orderDetailets;

    @TableField(exist = false)
    private List<OrderTime> orderTimes;

    @TableField(exist = false)
    private OrderAddress orderAddress;

//    @TableField(exist = false)
//    private Coupon coupon;

//    @TableField(exist = false)
//    private List<OrderDetailetAndPhoneAndPhoneDetailetAndComboAndAssureDTO> DTOList;

    @TableField(exist = false)
    private List<CartAndUserAndPhoneAndPhoneDetailetDTO> cartDTOList;

}
