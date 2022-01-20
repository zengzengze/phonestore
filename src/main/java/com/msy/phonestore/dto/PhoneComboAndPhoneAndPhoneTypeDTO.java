package com.msy.phonestore.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.msy.phonestore.pojo.PhoneAssure;
import com.msy.phonestore.pojo.PhoneDetailet;
import com.msy.phonestore.pojo.PhoneType;
import lombok.Data;

import java.util.List;

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
