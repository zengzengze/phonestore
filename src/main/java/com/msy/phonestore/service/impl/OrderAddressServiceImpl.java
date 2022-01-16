package com.msy.phonestore.service.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.AddressAndProvinceAndCityAndCountyDTO;
import com.msy.phonestore.mapper.OrderAddressMapper;
import com.msy.phonestore.pojo.*;
import com.msy.phonestore.service.ifc.IOrderAddressService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/16/17:36
 * @Description:
 */
@Service
public class OrderAddressServiceImpl implements IOrderAddressService {
    @Autowired
    private OrderAddressMapper orderAddressMapper;

    @Override
    public ResponseModel findOrderAddressMsgById(Map<String, Object> map) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
                .selectAll(OrderAddress.class)
                .select(Province::getProvince)
                .select(City::getCity)
                .select(County::getCounty)
                .innerJoin(Province.class,Province::getProvinceId,OrderAddress::getProvinceId)
                .innerJoin(City.class,City::getCityId,OrderAddress::getCityId)
                .innerJoin(County.class,County::getCountyId,OrderAddress::getCountyId)
                .eq(map.get("orderId")!=null,OrderAddress::getOrderId,map.get("orderId"));
        AddressAndProvinceAndCityAndCountyDTO DTO = orderAddressMapper.selectJoinOne(AddressAndProvinceAndCityAndCountyDTO.class, mpjLambdaWrapper);

        return ResponseModel.success(ResCode.SUCCESS,DTO);
    }
}
