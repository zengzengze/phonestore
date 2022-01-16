package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName(value = "address")
@KeySequence(value = "SEQ_ADDRESS_ADDRESSID")
public class Address {

    @TableId(value = "addressId",type = IdType.INPUT)
    private Integer addressId;
    @TableField(value = "userId")
    private Integer userId;
    private Integer provinceId;
    private Integer cityId;
    private Integer countyId;
    private String address;
    private String name;
    private String phoneNumber;
    @TableField(value = "defaultState")
    private Integer defaultState;
    private Integer state;
    private String fixedNumber;

}
