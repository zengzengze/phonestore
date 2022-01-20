package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/20/14:24
 * @Description:
 */
@Data
@TableName(value = "CourierCompany")
public class CourierCompany {
    @TableId(value = "courierCompanyId")
    private Integer courierCompanyId;
    @TableField(value = "courierCompany")
    private String courierCompany;
    @TableField(value = "deliveryType")
    private String deliveryType;
}
