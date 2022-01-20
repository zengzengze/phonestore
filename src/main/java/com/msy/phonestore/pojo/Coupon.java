package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/07/20:07
 * @Description:
 */
@Data
public class Coupon {

    @TableId(value = "couponId")
    private Integer couponId;

    private String coupon;

    @TableField(value = "couponWorth")
    private double couponWorth;

    @TableField(value = "phoneId")
    private Integer phoneId;

    @TableField(value = "couponType")
    private Integer couponType;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "startTime")
    private Date startTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "endTime")
    private Date endTime;

    @TableField(value = "useLimit")
    private double useLimit;

    @TableField(value = "couponCount")
    private Integer couponCount;

    private String introduction;
    @TableField(value = "couponImg")
    private String couponImg;
    @TableField(value = "convertCode")
    private String convertCode;

    @TableField(exist = false)
    private Phone phone;

}
