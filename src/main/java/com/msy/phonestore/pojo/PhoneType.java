package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "Phone_Type")
public class PhoneType {

    @TableId(value = "pTypeId")
    private Integer pTypeId;
    private String ptye;
}
