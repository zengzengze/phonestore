package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.UserCoupon;
import com.msy.phonestore.vo.ResponseModel;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/07/20:12
 * @Description:
 */
public interface IUserCouponService {

    //查询可用优惠券
    ResponseModel findCouponListMsg(Map<String,Object> map)throws Exception;

    //查询无门槛券
    ResponseModel findUnAvailableCouponList(Map<String,Object> map)throws Exception;

    //添加优惠券
    ResponseModel insertCouponMsg(UserCoupon userCoupon)throws Exception;

    //兑换优惠券
    ResponseModel convertCouponMsg(Map<String,Object> map)throws Exception;

    //退回优惠券
    ResponseModel updateUserCoupon(UserCoupon userCoupon)throws Exception;


    ResponseModel findUserCouponById(Integer userCouponId)throws Exception;

}
