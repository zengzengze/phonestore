package com.msy.phonestore.controller;

import com.msy.phonestore.service.ifc.ICourierCompanyService;
import com.msy.phonestore.service.ifc.IOrderTimeService;
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
 * @Date: 2022/01/07/17:34
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/courierCompany")
public class CourierCompanyController {
    @Autowired
    private ICourierCompanyService courierCompanyService;


    @RequestMapping("/findCourierCompanyList")
    public ResponseModel findCourierCompanyList(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = courierCompanyService.findCourierCompanyList(map);
        return model;
    }

}
