package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName(value = "Order_Detail")
@KeySequence(value = "SEQ_ODETAILET_ORDERDETAILETID")
public class OrderDetail {
    @TableId(value = "orderDetailId",type = IdType.INPUT)
    private Integer orderDetailId;
    @TableField(value = "orderId")
    private String orderId;

    @TableField(value = "phoneDetailId")
    private Integer phoneDetailId;

    @TableField(value = "phoneCount")
    private Integer phoneCount;

    @TableField(value = "comboId")
    private Integer comboId;

    @TableField(value = "assureId")
    private Integer assureId;

    @TableField(value = "assureCount")
    private Integer assureCount;

    @TableField(exist = false)
    private Orders orders;
    @TableField(exist = false)
    private PhoneDetail phoneDetail;
    @TableField(exist = false)
    private PhoneCombo phoneCombo;
    @TableField(exist = false)
    private PhoneAssure phoneAssure;

}
