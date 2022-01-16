package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/07/20:09
 * @Description:
 */
@Data
@TableName(value = "user_coupon")
@KeySequence(value = "SEQ_USERCOUPON_USERCOUPONID")
public class UserCoupon {
    @TableId(value = "userCouponId",type = IdType.INPUT)
    private Integer userCouponId;
    @TableField(value = "userId")
    private Integer userId;
    @TableField(value = "couponId")
    private Integer couponId;
    @TableField(value = "userCouponCount")
    private Integer userCouponCount;

    @TableField(exist = false)
    private Coupon coupon;
}
