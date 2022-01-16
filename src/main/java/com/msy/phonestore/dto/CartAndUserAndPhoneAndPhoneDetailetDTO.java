package com.msy.phonestore.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/12/25/16:02
 * @Description:
 */
@Data
public class CartAndUserAndPhoneAndPhoneDetailetDTO {
    private Integer cartId;
    private Integer userId;
    private Integer phoneDetailetId;
    private Integer quantity;
    private Integer comboId;
    private Integer assureId;
    private Integer cartState;

    private String userName;
    private String name;
    private String password;
    private Integer gender;
    private String birthday;
    private String userImg;
    private String province;
    private String city;
    private Integer grade;
    private String email;
    private String pNumber;

    private Integer phoneId;
    private String pName;
    private Integer pTypeId;
    private String phoneImg;
    private Integer remark;
    private double praise;

    private String color;
    private String ram;
    private String storage;
    private double price;
    private String screenSize;
    private String version;

    private String assure;
    private double assurePrice;
    private String assureImg;

    private String combo;
    private double comboPrice;
//
//    //小计
//    private double subtotal;
//    //总计
//    private double total;
}
