package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/16/10:55
 * @Description:
 */
@Data
@TableName(value = "order_Time")
@KeySequence(value = "SEQ_ODETAILET_ORDERDETAILETID")
public class OrderTime {

    @TableId(value = "orderTimeId",type = IdType.INPUT)
    private Integer orderTimeId;
    @TableField(value = "orderId")
    private String orderId;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "orderTime")
    private Date orderTime;

    @TableField(value = "orderTimeContent")
    private String orderTimeContent;
}
