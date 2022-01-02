package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "address")
public class Address {

    @TableId(value = "addressId")
    private Integer addressId;
    @TableField(value = "userId")
    private Integer userId;
    private String province;
    private String city;
    private String county;
    private String address;
    private String name;
    private String phone;
    @TableField(value = "defaultState")
    private Integer defaultState;
    private Integer state;

}
