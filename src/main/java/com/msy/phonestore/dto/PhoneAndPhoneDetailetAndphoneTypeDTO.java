package com.msy.phonestore.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/20/12:15
 * @Description:
 */
@Data
public class PhoneAndPhoneDetailetAndphoneTypeDTO {

    private Integer phoneId;
    private String phoneName;
    private Integer phoneTypeId;
    private String phoneImg;
    private Integer remark;
    private double praise;

    private Integer phoneDetailetId;
    private String color;
    private String ram;
    private String storage;
    private double price;
    private Integer quantity;
    private String screenSize;
    private String version;

    private String phoneType;
}
