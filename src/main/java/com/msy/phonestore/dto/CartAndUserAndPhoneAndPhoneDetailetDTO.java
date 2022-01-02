package com.msy.phonestore.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private Integer pDetailetId;
    private Integer quantity;

    private String userName;
    private String name;
    private String password;
    private Integer gender;
    private String birthday;
    private String userImg;
    private String province;
    private String city;
    private String uAddress;
    private Integer grade;
    private String email;
    private String pNumber;

    private Integer phoneId;
    private String pname;
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
//
//    //小计
//    private double subtotal;
//    //总计
//    private double total;
}
