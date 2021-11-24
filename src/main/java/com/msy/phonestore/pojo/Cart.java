package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "cart")
public class Cart {
    @TableId(value = "cartId")
    private Integer cartId;
    @TableField(value = "userId")
    private Integer userId;
    @TableField(value = "pdateiletId")
    private Integer pdateiletId;
    private Integer quantity;
    private double subtotal;
    private double total;
}
