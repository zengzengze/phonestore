package com.msy.phonestore.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msy.phonestore.dto.DeliveryToolsInfo;
import com.msy.phonestore.mapper.ParcelMapper;
import com.msy.phonestore.pojo.Parcel;
import com.msy.phonestore.service.ifc.IParcelService;
import com.msy.phonestore.vo.DeliveryToolUtils;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/13/22:55
 * @Description:
 */
@Service
public class ParcelServiceImpl implements IParcelService {

    @Autowired
    private ParcelMapper parcelMapper;

    @Override
    public ResponseModel findParcelByMap(Map<String, Object> map) throws Exception {
        Parcel parcel= parcelMapper.selectOne(new QueryWrapper<Parcel>()
                .eq("orderId",map.get("orderId")));
        if(parcel!=null && parcel.getDeliveryId()!=null){
            Map<String,Object> findDeliveryMap = JSON.parseObject(JSON.toJSONString(parcel), Map.class);
            DeliveryToolsInfo deliveryToolsInfo = DeliveryToolUtils.findDeliveryMsg(findDeliveryMap);
            parcel.setDeliveryToolsInfo(deliveryToolsInfo);
        }
        return ResponseModel.success(ResCode.SUCCESS,parcel);
    }
}
