package com.msy.phonestore.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/13/17:15
 * @Description:
 */
@Data
public class PhoneAndPhoneDetailetAndComboAndAssureDTO {


    private Integer phoneDetailetId;
    private Integer phoneId;
    private String color;
    private String ram;
    private String storage;
    private double price;
    private Integer quantity;
    private String screenSize;
    private String version;

    private String phoneName;
    private String phoneImg;

    private String combo;
    @TableField(value ="comboPrice")
    private double comboPrice;

    private String assure;
    @TableField(value = "assurePrice")
    private double assurePrice;
    private String assureImg;

}
