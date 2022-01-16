package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/25/13:06
 * @Description:
 */
@Data
@TableName(value = "Phone_Type")
@KeySequence(value = "SEQ_PHONETYPE_PHONETYPEID")
public class PhoneType {

    @TableId(value = "phoneTypeId",type = IdType.INPUT)
    private Integer phoneTypeId;
    @TableField(value = "phoneType")
    private String phoneType;
}
