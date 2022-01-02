package com.msy.phonestore.pojo;


import com.baomidou.mybatisplus.annotation.*;

@TableName(value = "cart")
@KeySequence(value = "SEQ_CART_CARTID")
public class Cart {

    @TableId(value = "cartId",type = IdType.INPUT)
    private Integer cartId;

    @TableField(value = "userId")
    private Integer userId;

    @TableField(value = "pDetailetId")
    private Integer pDetailetId;
    private Integer quantity;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getpDetailetId() {
        return pDetailetId;
    }

    public void setpDetailetId(Integer pDetailetId) {
        this.pDetailetId = pDetailetId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
