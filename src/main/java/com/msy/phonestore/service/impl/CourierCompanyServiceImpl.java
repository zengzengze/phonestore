package com.msy.phonestore.service.impl;

import com.msy.phonestore.mapper.CourierCompanyMapper;
import com.msy.phonestore.pojo.CourierCompany;
import com.msy.phonestore.service.ifc.ICourierCompanyService;
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
 * @Date: 2022/01/20/15:40
 * @Description:
 */
@Service
public class CourierCompanyServiceImpl implements ICourierCompanyService {

    @Autowired
    private CourierCompanyMapper courierCompanyMapper;
    @Override
    public ResponseModel findCourierCompanyList(Map<String, Object> map) throws Exception {
        List<CourierCompany> courierCompanyList = courierCompanyMapper.selectByMap(map);
        return ResponseModel.success(ResCode.SUCCESS,courierCompanyList);
    }
}
