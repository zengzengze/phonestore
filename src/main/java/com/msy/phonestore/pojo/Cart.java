package com.msy.phonestore.pojo;


import com.baomidou.mybatisplus.annotation.*;

@TableName(value = "cart")
@KeySequence(value = "SEQ_CART_CARTID")
public class Cart {

    @TableId(value = "cartId",type = IdType.INPUT)
    private Integer cartId;

    @TableField(value = "userId")
    private Integer userId;

    @TableField(value = "phoneDetailId")
    private Integer phoneDetailId;
    private Integer quantity;
    private Integer comboId;
    private Integer assureId;
    private Integer cartState;

    @TableField(exist = false)
    private PhoneDetail phoneDetail;
    @TableField(exist = false)
    private PhoneCombo phoneCombo;
    @TableField(exist = false)
    private PhoneAssure phoneAssure;

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

    public Integer getPhoneDetailId() {
        return phoneDetailId;
    }

    public void setPhoneDetailId(Integer phoneDetailId) {
        this.phoneDetailId = phoneDetailId;
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

    public Integer getCartState() {
        return cartState;
    }

    public void setCartState(Integer cartState) {
        this.cartState = cartState;
    }

    public PhoneDetail getPhoneDetail() {
        return phoneDetail;
    }

    public void setPhoneDetail(PhoneDetail phoneDetail) {
        this.phoneDetail = phoneDetail;
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
}
