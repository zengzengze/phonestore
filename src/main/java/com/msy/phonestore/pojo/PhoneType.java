package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class PhoneType {

    @TableId(value = "pTypeId")
    private Integer pTypeId;
    @TableField(value = "pType")
    private String pType;
}
