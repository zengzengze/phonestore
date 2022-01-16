package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName(value = "Phone_Detailet")
@KeySequence(value = "SEQ_PHONEDETAILET_PDETAILETID")
public class PhoneDetailet {

    @TableId(value = "phoneDetailetId",type = IdType.INPUT)
    private Integer phoneDetailetId;

    @TableField(value = "phoneId")
    private Integer phoneId;
    private String color;

    private String ram;
    private String storage;
    private double price;
    private Integer quantity;

    @TableField(value = "screenSize")
    private String screenSize;
    private String version;

    @TableField(exist = false)
    private Phone phone;
}
