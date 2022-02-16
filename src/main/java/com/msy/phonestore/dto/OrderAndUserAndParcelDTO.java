package com.msy.phonestore.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/20/13:29
 * @Description:
 */
@Data
public class OrderAndUserAndParcelDTO {

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

    @TableField(value = "userName")
    private String userName;
    private String name;
    private String password;
    private Integer gender;
    private String birthday;
    @TableField(value = "userImg")
    private String userImg;
    private String province;
    private String city;
    private Integer grade;
    private String email;
    @TableField(value = "phoneNumber")
    private String phoneNumber;

    private Integer parcelId;
    @TableField(value = "deliveryId")
    private String deliveryId;
    @TableField(value = "courierCompanyId")
    private String courierCompanyId;
}
