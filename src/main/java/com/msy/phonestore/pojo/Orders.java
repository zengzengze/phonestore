package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.msy.phonestore.dto.AddressAndProvinceAndCityAndCountyDTO;
import com.msy.phonestore.dto.CartAndUserAndPhoneAndPhoneDetailDTO;
import lombok.Data;

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

    @TableField(value = "userCouponId")
    private Integer userCouponId;

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
    private List<OrderDetail> orderDetailList;

    @TableField(exist = false)
    private List<OrderTime> orderTimes;

    @TableField(exist = false)
    private OrderAddress orderAddress;

    @TableField(exist = false)
    private Users users;

    @TableField(exist = false)
    private Parcel parcel;

    @TableField(exist = false)
    private AddressAndProvinceAndCityAndCountyDTO orderAddressDTO;

    @TableField(exist = false)
    private List<CartAndUserAndPhoneAndPhoneDetailDTO> cartDTOList;

}
