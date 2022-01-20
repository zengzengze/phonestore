package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.Coupon;
import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/07/20:12
 * @Description:
 */
public interface ICouponService {

    //查询可用优惠券
    ResponseModel findCouponListMsg(Map<String,Object> map)throws Exception;

    //查询订单使用的优惠券
    ResponseModel findCouponById(Integer couponId)throws Exception;

    //修改优惠券
    ResponseModel updateCouponMsg(Coupon coupon)throws Exception;

    //删除优惠券
    ResponseModel deleteCouponById(Integer[] ids)throws Exception;

    //后台使用
    ResponseModel findCouponListPagMsg(Map<String,Object> map)throws Exception;
}
