package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msy.phonestore.mapper.CityMapper;
import com.msy.phonestore.mapper.CountyMapper;
import com.msy.phonestore.mapper.ProvinceMapper;
import com.msy.phonestore.pojo.City;
import com.msy.phonestore.pojo.County;
import com.msy.phonestore.pojo.Province;
import com.msy.phonestore.service.ifc.ILocationService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/05/9:59
 * @Description:
 */
@Service
public class LocationServiceImpl implements ILocationService {

    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CountyMapper countyMapper;

    @Override
    public ResponseModel findProvinceList(Integer id) throws Exception {
        List<Province> provinces = provinceMapper.selectList(new QueryWrapper<Province>()
                .eq(id!=null,"provinceId", id)
                .orderByAsc("provinceId"));
        return ResponseModel.success(ResCode.SUCCESS,provinces);
    }

    @Override
    public ResponseModel findCityListById(Integer id) throws Exception {

        List<City> cities = cityMapper.selectList(new QueryWrapper<City>()
                .eq("provinceId", id)
                .orderByAsc("cityId"));

        return ResponseModel.success(ResCode.SUCCESS,cities);
    }

    @Override
    public ResponseModel findCountyListById(Integer id) throws Exception {
        List<County> counties = countyMapper.selectList(new QueryWrapper<County>()
                .eq("cityId", id)
                .orderByAsc("countyId"));

        return ResponseModel.success(ResCode.SUCCESS,counties);
    }
}
