package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "phone")
public class Phone {

    @TableId(value = "phoneId")
    private Integer phoneId;
    private String pname;

    @TableField(value = "pTypeId")
    private Integer pTypeId;

    @TableField(value = "phoneImg")
    private String phoneImg;
    private Integer remark;
    private double praise;

    private PhoneType phoneType;
    private PhoneDetailet phoneDetailet;

}
