package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.Coupon;
import com.msy.phonestore.pojo.Integral;
import com.msy.phonestore.service.ifc.ICouponService;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/10/13:21
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private ICouponService couponService;

    @RequestMapping("/findCouponListMsg")
    public ResponseModel findMsgByID(@RequestBody Map<String,Object> map)throws Exception{
        System.out.println(map);
        ResponseModel model = couponService.findCouponListMsg(map);
        return model;
    }

    @RequestMapping("/findCouponById")
    public ResponseModel findCouponById(Integer couponId)throws Exception{
        ResponseModel model = couponService.findCouponById(couponId);
        return model;
    }

    @RequestMapping("/updateCouponMsg")
    public ResponseModel updateCouponMsg(@RequestBody Coupon coupon)throws Exception{
        ResponseModel model = couponService.updateCouponMsg(coupon);
        return model;
    }

    @RequestMapping("/deleteCouponById")
    public ResponseModel deleteCouponById(@RequestBody Map<String,Integer[]> map)throws Exception{
        ResponseModel model = couponService.deleteCouponById(map.get("ids"));
        return model;
    }
}
