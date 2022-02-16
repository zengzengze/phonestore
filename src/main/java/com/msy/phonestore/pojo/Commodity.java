package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/30/11:12
 * @Description:
 */
@Data
@TableName(value = "commodity")
@KeySequence(value = "seq_commodity_commodityId")
public class Commodity {
    @TableId(value = "commodityId")
    private Integer commodityId;
    @TableField(value = "commodityName")
    private String commodityName;
    @TableField(value = "commodityPrice")
    private double commodityPrice;
    @TableField(value = "commodityCount")
    private Integer commodityCount;
    @TableField(value = "commodityImg")
    private String commodityImg;
    @TableField(value = "commodityColor")
    private String commodityColor;
    @TableField(value = "productTypeId")
    private Integer productTypeId;
}
