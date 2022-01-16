package com.msy.phonestore.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/25/10:57
 * @Description:
 */
@Data
public class PhoneAndTypeAndDetailetAndImgDTO {
    private Integer phoneId;

    private String phoneName;
    private Integer phoneTypeId;
    private String phoneImg;
    private Integer remark;
    private double praise;

    private String pType;

    private Integer phoneDetailetId;
    private String color;
    private String ram;
    private String storage;
    private double price;
    private Integer quantity;
    private String screenSize;
    private String version;


}
