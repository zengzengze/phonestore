package com.msy.phonestore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/11/17:22
 * @Description:
 */
@Data
public class UserCouponAndCouponDTO {

    private Integer userCouponId;
    private Integer userId;
    private Integer userCouponCount;

    private Integer couponId;
    private String coupon;
    private Double couponWorth;
    private Integer phoneId;
    private Integer couponType;
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Date endTime;
    private Double useLimit;
    private Integer couponCount;
    private String introduction;
    private String couponImg;


}
