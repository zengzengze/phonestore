package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName(value = "Order_Detailet")
@KeySequence(value = "SEQ_ODETAILET_ORDERDETAILETID")
public class OrderDetailet {
    @TableId(value = "orderDetailetId",type = IdType.INPUT)
    private Integer orderDetailetId;
    @TableField(value = "orderId")
    private String orderId;

    @TableField(value = "phoneDetailetId")
    private Integer phoneDetailetId;

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
    private PhoneDetailet phoneDetailet;
    @TableField(exist = false)
    private PhoneCombo phoneCombo;
    @TableField(exist = false)
    private PhoneAssure phoneAssure;

}
