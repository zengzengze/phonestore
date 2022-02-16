package com.msy.phonestore.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.msy.phonestore.pojo.Commodity;
import com.msy.phonestore.pojo.PhoneAssure;
import com.msy.phonestore.pojo.PhoneCombo;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/12/25/16:02
 * @Description:
 */
@Data
public class CartAndUserAndPhoneAndPhoneDetailDTO {
    private Integer cartId;
    private Integer userId;
    private Integer phoneDetailId;
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
    private String phoneNumber;

    private Integer phoneId;
    private String phoneName;
    private Integer phoneTypeId;
    private String phoneImg;
    private Integer remark;
    private double praise;

    private String color;
    private String ram;
    private String storage;
    private double price;
    private String screenSize;
    private String version;

//    private String assure;
//    private double assurePrice;
//    private String assureImg;

    private String combo;
    private double comboPrice;
    private Integer commodityId;

    @TableField(exist = false)
    private Commodity commodity;

    @TableField(exist = false)
    private PhoneCombo phoneCombo;

    @TableField(exist = false)
    private PhoneAssure phoneAssure;

    @TableField(exist = false)
    private List<PhoneAssure> phoneAssureList;
//
//    //小计
//    private double subtotal;
//    //总计
//    private double total;
}
