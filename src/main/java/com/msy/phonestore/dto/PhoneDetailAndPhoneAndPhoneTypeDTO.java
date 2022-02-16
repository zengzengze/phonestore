package com.msy.phonestore.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.msy.phonestore.pojo.PhoneAssure;
import com.msy.phonestore.pojo.PhoneCombo;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/29/23:19
 * @Description:
 */
@Data
public class PhoneDetailAndPhoneAndPhoneTypeDTO {

    private Integer phoneDetailId;
    private Integer phoneId;
    private String color;
    private String ram;
    private String storage;
    private double price;
    private String screenSize;
    private String version;

    private String phoneName;
    private String phoneImg;

    private String phoneType;

    @TableField(exist = false)
    private PhoneAssure phoneAssure;

    @TableField(exist = false)
    private PhoneCombo phoneCombo;
}
