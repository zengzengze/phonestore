package com.msy.phonestore.service.impl;

import com.msy.phonestore.mapper.CommodityMapper;
import com.msy.phonestore.pojo.Commodity;
import com.msy.phonestore.service.ifc.ICommodityService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/30/11:31
 * @Description:
 */
@Service
public class CommdityServiceImpl implements ICommodityService {

    @Autowired
    private CommodityMapper commodityMapper;


    @Override
    public ResponseModel findCommodityListByMap(Map<String, Object> map) throws Exception {
        List<Commodity> commodityList = commodityMapper.selectByMap(map);
        return ResponseModel.success(ResCode.SUCCESS,commodityList);
    }

    @Override
    public ResponseModel findCommodityById(Integer commodityId) throws Exception {
        Commodity commodity = commodityMapper.selectById(commodityId);
        return ResponseModel.success(ResCode.SUCCESS,commodity);
    }

    @Override
    public ResponseModel findCommodityByIds(Integer[] ids) throws Exception {
        List<Commodity> commodityList=new ArrayList<>();

        for(int i=0;i<ids.length;i++){
            Commodity commodity = commodityMapper.selectById(ids[i]);
            commodityList.add(commodity);
        }
        return ResponseModel.success(ResCode.SUCCESS,commodityList);
    }
}
