package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "Order_Detailet")
public class OrderDetailet {
    @TableId(value = "odId")
    private Integer odId;
    @TableField(value = "orderId")
    private Integer orderId;
    @TableField(value = "phoneId")
    private Integer phoneId;
    private Integer quantity;


}
