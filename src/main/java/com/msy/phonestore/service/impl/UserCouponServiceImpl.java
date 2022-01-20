package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.UserCouponAndCouponDTO;
import com.msy.phonestore.mapper.CouponMapper;
import com.msy.phonestore.mapper.PhoneMapper;
import com.msy.phonestore.mapper.UserCouponMapper;
import com.msy.phonestore.pojo.Coupon;
import com.msy.phonestore.pojo.UserCoupon;
import com.msy.phonestore.service.ifc.ICouponService;
import com.msy.phonestore.service.ifc.IUserCouponService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/07/20:13
 * @Description:
 */
@Service
public class UserCouponServiceImpl implements IUserCouponService {

    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private UserCouponMapper userCouponMapper;
    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    public ResponseModel findCouponListMsg(Map<String, Object> map) throws Exception {
        List<UserCoupon> userCouponList = userCouponMapper.queryMsgByMap(map);
        return ResponseModel.success(ResCode.SUCCESS,userCouponList);

//        Date date = new Date();
//        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
//                .selectAll(UserCoupon.class)
//                .select(Coupon::getCoupon,Coupon::getCouponType,Coupon::getCouponWorth,Coupon::getUseLimit,Coupon::getStartTime,
//                        Coupon::getEndTime,Coupon::getIntroduction,Coupon::getCouponImg)
//                .innerJoin(Coupon.class,Coupon::getCouponId,UserCoupon::getCouponId)
//                .le(map.get("userLimit")!=null,Coupon::getUseLimit,map.get("userLimit"))
//                .ge(Coupon::getEndTime,date)
//                .le(Coupon::getStartTime,date)
//                .eq(map.get("phoneId")!=null,Coupon::getPhoneId,map.get("phoneId"))
//                .or()
//                .eq(Coupon::getCouponType,0)
//                .eq(map.get("userId")!=null,UserCoupon::getUserId,map.get("userId"));
//        List<UserCouponAndCouponDTO> DTOS = userCouponMapper.selectJoinList(UserCouponAndCouponDTO.class, mpjLambdaWrapper);
//        return ResponseModel.success(ResCode.SUCCESS,DTOS);
    }

    @Override
    public ResponseModel findUnAvailableCouponList(Map<String,Object> map) throws Exception {
//        Date date=new Date();
//        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
//                .selectAll(UserCoupon.class)
//                .select(Coupon::getCoupon,Coupon::getCouponType,Coupon::getCouponWorth,Coupon::getUseLimit,Coupon::getStartTime,
//                        Coupon::getEndTime,Coupon::getIntroduction,Coupon::getCouponImg)
//                .innerJoin(Coupon.class,Coupon::getCouponId,UserCoupon::getCouponId)
//                .lt(Coupon::getEndTime,date)
//                .or()
//                .gt(Coupon::getStartTime,date)
//                .lt(map.get("userLimit")!=null,Coupon::getUseLimit,map.get("userLimit"))
//                .eq(map.get("userId")!=null,UserCoupon::getUserId,map.get("userId"));
//        List<UserCouponAndCouponDTO> DTOS = userCouponMapper.selectJoinList(UserCouponAndCouponDTO.class, mpjLambdaWrapper);
//        return ResponseModel.success(ResCode.SUCCESS,DTOS);

        List<UserCoupon> userCouponList = userCouponMapper.findUnAvailableCouponList(map);
        return ResponseModel.success(ResCode.SUCCESS,userCouponList);
    }

    @Override
    public ResponseModel insertCouponMsg(UserCoupon userCoupon) throws Exception {

        int row = userCouponMapper.insert(userCoupon);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel convertCouponMsg(Map<String, Object> map) throws Exception {
        Coupon coupon=couponMapper.selectOne(new QueryWrapper<Coupon>().eq("convertCode",map.get("convertCode")));

        UserCoupon userCoupon1 = userCouponMapper.selectOne(new QueryWrapper<UserCoupon>()
                .eq("userId", map.get("userId"))
                .eq("couponId", coupon.getCouponId()));

        if(userCoupon1==null){
            if(coupon.getCouponCount()-1!=-1){

                UserCoupon userCoupon=new UserCoupon();
                userCoupon.setCouponId(coupon.getCouponId());
                userCoupon.setUserId((Integer) map.get("userId"));
                userCoupon.setUserCouponCount(1);
                int row = userCouponMapper.insert(userCoupon);

                coupon.setCouponCount(coupon.getCouponCount()-1);
                int row1 = couponMapper.updateById(coupon);

                if(row>0 && row1>0){
                    return ResponseModel.success(ResCode.SUCCESS);
                }else {
                    return ResponseModel.fail(ResCode.FAIL);
                }

            }else {
                return ResponseModel.fail(ResCode.FAIL,"优惠券已兑完!");
            }

        }else {
            return ResponseModel.fail(ResCode.FAIL,"你已经兑换过优惠券了!");
        }
    }

    @Override
    public ResponseModel updateUserCoupon(UserCoupon userCoupon) throws Exception {

        //退回优惠券
        userCoupon.setUserCouponCount(1);
        int row = userCouponMapper.updateById(userCoupon);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel findUserCouponById(Integer userCouponId) throws Exception {
        UserCoupon userCoupon = userCouponMapper.findUserCouponBuId(userCouponId);
        return ResponseModel.success(ResCode.SUCCESS,userCoupon);
    }
}
