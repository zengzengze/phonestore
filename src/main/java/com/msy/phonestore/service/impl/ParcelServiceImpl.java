package com.msy.phonestore.service.impl;

import com.msy.phonestore.mapper.ParcelMapper;
import com.msy.phonestore.pojo.Parcel;
import com.msy.phonestore.service.ifc.IParcelService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        List<Parcel> parcels = parcelMapper.selectByMap(map);
        return ResponseModel.success(ResCode.SUCCESS,parcels);
    }
}
