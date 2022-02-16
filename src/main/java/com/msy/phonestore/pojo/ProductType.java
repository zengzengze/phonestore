package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/30/10:45
 * @Description:
 */
@Data
@TableName(value = "product_type")
public class ProductType {

    @TableId(value = "productTypeId")
    private Integer productTypeId;

    private String productType;
}
