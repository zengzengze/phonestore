package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/16/17:23
 * @Description:
 */
@Data
@TableName(value = "order_address")
@KeySequence(value = "SEQ_ORDERADDRESS_OADDRESSID")
public class OrderAddress {

    @TableId(value = "orderAddressId",type = IdType.INPUT)
    private Integer orderAddressId;

    @TableField(value = "orderId")
    private String orderId;

    @TableField(value = "name")
    private String name;

    @TableField(value = "provinceId")
    private Integer provinceId;

    @TableField(value = "cityId")
    private Integer cityId;

    @TableField(value = "countyId")
    private Integer countyId;

    @TableField(value = "address")
    private String address;

    @TableField(value = "phoneNumber")
    private String phoneNumber;

    @TableField(value = "fixedNumber")
    private String fixedNumber;
}
