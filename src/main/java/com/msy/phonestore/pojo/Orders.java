package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "orders")
public class Orders {
    @TableId(value = "orderId")
    private Integer orderId;
    @TableField(value = "userId")
    private Integer userId;
    @TableField(value = "orderDtae")
    private Date orderDtae;
    @TableField(value = "orderTotal")
    private double orderTotal;
    @TableField(value = "addressId")
    private Integer addressId;
    @TableField(value = "orderState")
    private Integer orderState;
    private Users users;
    private Address address;
}
