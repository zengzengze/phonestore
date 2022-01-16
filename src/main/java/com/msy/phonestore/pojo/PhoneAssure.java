package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/02/16:09
 * @Description:
 */
@Data
@TableName(value = "Phone_Assure")
@KeySequence(value = "SEQ_PHONEASSURE_ASSUREID")
public class PhoneAssure {
    @TableId(value = "assureId",type = IdType.INPUT)
    private Integer assureId;
    private String assure;
    @TableField(value = "assurePrice")
    private double assurePrice;
    private Integer phoneId;
    private String assureImg;
}
