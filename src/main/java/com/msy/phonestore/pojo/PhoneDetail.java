package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@TableName(value = "Phone_Detail")
@KeySequence(value = "SEQ_PHONEDetailet_PDetailetID")
public class PhoneDetail {

    @TableId(value = "phoneDetailId",type = IdType.INPUT)
    private Integer phoneDetailId;

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

    @TableField(exist = false)
    private String[] imgList;

    @TableField(exist = false)
    private PhoneImg phoneImg;


}
