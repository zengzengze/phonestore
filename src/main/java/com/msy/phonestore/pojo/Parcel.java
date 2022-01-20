package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/13/21:51
 * @Description:
 */
@Data
@TableName(value = "parcel")
@KeySequence(value = "SEQ_PARCEL_PARCELID")
public class Parcel {
    @TableId(value = "parcelId",type = IdType.INPUT)
    private Integer parcelId;
    @TableField(value = "orderId")
    private String orderId;
    @TableField(value = "deliveryId")
    private String deliveryId;
    @TableField(value = "courierCompanyId")
    private String courierCompanyId;

    @TableField(value = "phoneNumber")
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "deliveryTime")
    private Date deliveryTime;
}
