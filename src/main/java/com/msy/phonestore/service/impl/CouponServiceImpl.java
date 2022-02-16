package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msy.phonestore.mapper.CouponMapper;
import com.msy.phonestore.mapper.PhoneMapper;
import com.msy.phonestore.mapper.UserCouponMapper;
import com.msy.phonestore.pojo.Coupon;
import com.msy.phonestore.pojo.Users;
import com.msy.phonestore.service.ifc.ICouponService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/07/20:13
 * @Description:
 */
@Service
public class CouponServiceImpl implements ICouponService {

    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private UserCouponMapper userCouponMapper;
    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    public ResponseModel findCouponListMsg(Map<String,Object> map) throws Exception {

//        Date date = new Date();
////        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//
//        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
//                .selectAll(Coupon.class)
//                .select(UserCoupon::getUserCouponId,UserCoupon::getUserCouponCount,UserCoupon::getUserId)
//                .select(Phone::getPname,Phone::getPhoneImg,Phone::getPTypeId)
//                .innerJoin(UserCoupon.class,UserCoupon::getCouponId,Coupon::getCouponId)
//                .innerJoin(Phone.class,Phone::getPhoneId,Coupon::getPhoneId)
//                .eq(map.get("phoneId")!=null,Coupon::getPhoneId,map.get("phoneId"))
//                .ge(Coupon::getEndTime,date)
//                .le(Coupon::getStartTime,date)
//                .or()
//                .eq(Coupon::getCouponType,0)
//                .eq(UserCoupon::getUserId,map.get("userId"));
//
//        List<CouponAndUserCouponAndPhone> DTOS = couponMapper.selectJoinList(CouponAndUserCouponAndPhone.class, mpjLambdaWrapper);
        return null;
    }

    @Override
    public ResponseModel findCouponById(Integer couponId) throws Exception {
        Coupon coupon = couponMapper.selectById(couponId);
        return ResponseModel.success(ResCode.SUCCESS,coupon);
    }

    @Override
    public ResponseModel updateCouponMsg(Coupon coupon) throws Exception {
        int row = couponMapper.updateById(coupon);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel deleteCouponById(Integer[] ids) throws Exception {
        int row=0;
        for(int i=0;i<ids.length;i++){
            row += couponMapper.deleteById(ids[i]);
        }
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel findCouponListPagMsg(Map<String, Object> map) throws Exception {
        QueryWrapper queryWrapper=new QueryWrapper<Coupon>()
                .like(map.get("coupon")!=null && map.get("coupon")!="","coupon",map.get("coupon"))
                .eq(map.get("couponType")!=null && map.get("couponType")!="","couponType",map.get("couponType"));

        Page userPage=new Page<>((Integer)map.get("pageNumber"),(Integer)map.get("pageSize"));
        Page page = couponMapper.selectPage(userPage, queryWrapper);
        return ResponseModel.success(ResCode.SUCCESS,page);
    }
}
