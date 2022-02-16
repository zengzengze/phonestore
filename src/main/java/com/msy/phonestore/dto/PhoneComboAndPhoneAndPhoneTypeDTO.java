package com.msy.phonestore.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/20/12:49
 * @Description:
 */
@Data
public class PhoneComboAndPhoneAndPhoneTypeDTO {
    private Integer comboId;
    private String combo;
    private double comboPrice;
    private Integer phoneId;
    private Integer quantity;

    private String phoneName;
    private Integer phoneTypeId;
    private String phoneImg;
    private Integer remark;
    private double praise;

    private String PhoneType;
}
