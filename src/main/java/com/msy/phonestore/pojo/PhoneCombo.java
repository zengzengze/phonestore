package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/02/16:06
 * @Description:
 */
@Data
@TableName(value = "Phone_Combo")
@KeySequence(value = "SEQ_PHONECOMBO_PHONE_COMBOID")
public class PhoneCombo {
    @TableId(value ="comboId",type = IdType.INPUT)
    private Integer comboId;
    private String combo;
    @TableField(value ="comboPrice")
    private double comboPrice;
    @TableField(value ="phoneId")
    private Integer phoneId;
    private Integer quantity;


}
