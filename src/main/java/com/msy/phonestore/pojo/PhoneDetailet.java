package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "Phone_Detailet")
public class PhoneDetailet {
    @TableId(value = "pDetailetId")
    private Integer pDetailetId;
    @TableField(value = "phoneId")
    private Integer phoneId;
    private String color;
    @TableField(value = "dphoneImg")
    private String dphoneImg;
    private String ram;
    private String storage;
    private double price;
    private Integer dquantity;
    @TableField(value = "screenSize")
    private String screenSize;
    private String version;
    private Phone phone;
}
