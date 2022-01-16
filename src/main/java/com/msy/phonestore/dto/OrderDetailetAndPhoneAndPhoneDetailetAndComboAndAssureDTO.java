package com.msy.phonestore.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/07/16:53
 * @Description:
 */
@Data
public class OrderDetailetAndPhoneAndPhoneDetailetAndComboAndAssureDTO {

    private Integer orderDetailetId;
    @TableField(value = "orderId")
    private Integer orderId;
    @TableField(value = "phoneId")
    private Integer phoneId;
    @TableField(value = "phoneCount")
    private Integer phoneCount;
    @TableField(value = "comboId")
    private Integer comboId;
    @TableField(value = "assureId")
    private Integer assureId;
    @TableField(value = "assureCount")
    private Integer assureCount;

    private String phoneName;

    private Integer phoneDetailetId;
    private String color;
    private String ram;
    private String storage;
    private double price;

    private String combo;
    @TableField(value ="comboPrice")
    private double comboPrice;

    private String assure;
    @TableField(value = "assurePrice")
    private double assurePrice;
    private String assureImg;
}
