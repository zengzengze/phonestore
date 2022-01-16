package com.msy.phonestore.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.msy.phonestore.pojo.Coupon;
import com.msy.phonestore.pojo.UserCoupon;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/07/20:11
 * @Description:
 */
public interface UserCouponMapper extends MPJBaseMapper<UserCoupon> {

    public List<UserCoupon> queryMsgByMap(Map<String,Object> map)throws Exception;

    public List<UserCoupon> findUnAvailableCouponList(Map<String,Object> map)throws Exception;

    @Update("update user_coupon set userCouponCount=userCouponCount-1 where userCouponId=#{userCouponId}")
    public int updateUserCouponCount(Integer userCouponId)throws Exception;
}
