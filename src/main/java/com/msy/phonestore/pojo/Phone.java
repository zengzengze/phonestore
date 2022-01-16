package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@TableName(value = "phone")
@KeySequence(value = "SEQ_PHONE_PHONEID")
public class Phone {

    @TableId(value = "phoneId",type = IdType.INPUT)
    private Integer phoneId;

    @TableField(value = "phoneName")
    private String phoneName;

    @TableField(value = "phoneTypeId")
    private Integer phoneTypeId;

    @TableField(value = "phoneImg")
    private String phoneImg;
    private Integer remark;
    private double praise;

    @TableField(exist = false)
    private PhoneType phoneType;

    @TableField(exist = false)
    private PhoneDetailet phoneDetailet;

    @TableField(exist = false)
    private List<PhoneAssure> phoneAssureList;

}
