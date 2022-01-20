package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.UserCoupon;
import com.msy.phonestore.service.ifc.ICouponService;
import com.msy.phonestore.service.ifc.IUserCouponService;
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
@RequestMapping("/userCoupon")
public class UserCouponController {

    @Autowired
    private IUserCouponService userCouponService;

    @RequestMapping("/findCouponListMsg")
    public ResponseModel findCouponListMsg(@RequestBody Map<String,Object> map)throws Exception{
        System.out.println(map);
        ResponseModel model = userCouponService.findCouponListMsg(map);
        return model;
    }


    @RequestMapping("/findUnAvailableCouponList")
    public ResponseModel findUnAvailableCouponList(@RequestBody Map<String,Object> map)throws Exception{
        System.out.println(map);
        ResponseModel model = userCouponService.findUnAvailableCouponList(map);
        return model;
    }

    @RequestMapping("/insertCouponMsg")
    public ResponseModel insertCouponMsg(@RequestBody UserCoupon userCoupon)throws Exception{
        ResponseModel model = userCouponService.insertCouponMsg(userCoupon);
        return model;
    }

    @RequestMapping("/convertCouponMsg")
    public ResponseModel convertCouponMsg(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = userCouponService.convertCouponMsg(map);
        return model;
    }

    @RequestMapping("/updateUserCoupon")
    public ResponseModel updateUserCoupon(@RequestBody UserCoupon userCoupon)throws Exception{
        ResponseModel model = userCouponService.updateUserCoupon(userCoupon);
        return model;
    }

    @RequestMapping("/findUserCouponById")
    public ResponseModel findUserCouponById(Integer userCouponId)throws Exception{
        ResponseModel model = userCouponService.findUserCouponById(userCouponId);
        return model;
    }

}
