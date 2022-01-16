package com.msy.phonestore.pojo;


import com.baomidou.mybatisplus.annotation.*;

@TableName(value = "cart")
@KeySequence(value = "SEQ_CART_CARTID")
public class Cart {

    @TableId(value = "cartId",type = IdType.INPUT)
    private Integer cartId;

    @TableField(value = "userId")
    private Integer userId;

    @TableField(value = "phoneDetailetId")
    private Integer phoneDetailetId;
    private Integer quantity;
    private Integer comboId;
    private Integer assureId;
    private Integer cartState;

    @TableField(exist = false)
    private PhoneDetailet phoneDetailet;
    @TableField(exist = false)
    private PhoneCombo phoneCombo;
    @TableField(exist = false)
    private PhoneAssure phoneAssure;

    public PhoneDetailet getPhoneDetailet() {
        return phoneDetailet;
    }

    public void setPhoneDetailet(PhoneDetailet phoneDetailet) {
        this.phoneDetailet = phoneDetailet;
    }

    public PhoneCombo getPhoneCombo() {
        return phoneCombo;
    }

    public void setPhoneCombo(PhoneCombo phoneCombo) {
        this.phoneCombo = phoneCombo;
    }

    public PhoneAssure getPhoneAssure() {
        return phoneAssure;
    }

    public void setPhoneAssure(PhoneAssure phoneAssure) {
        this.phoneAssure = phoneAssure;
    }

    public Integer getCartState() {
        return cartState;
    }

    public void setCartState(Integer cartState) {
        this.cartState = cartState;
    }

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

    public Integer getPhoneDetailetId() {
        return phoneDetailetId;
    }

    public void setPhoneDetailetId(Integer phoneDetailetId) {
        this.phoneDetailetId = phoneDetailetId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public Integer getAssureId() {
        return assureId;
    }

    public void setAssureId(Integer assureId) {
        this.assureId = assureId;
    }
}
