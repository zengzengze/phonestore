package com.msy.phonestore.controller;

import com.msy.phonestore.service.ifc.ILocationService;
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
 * @Date: 2022/01/05/10:04
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private ILocationService locationService;

    @RequestMapping("/findProvinceList")
    public ResponseModel findProvinceList(Integer id)throws Exception{
        ResponseModel model = locationService.findProvinceList(id);
        return model;
    }

    @RequestMapping("/findCityListById")
    public ResponseModel findCityListById(Integer id)throws Exception{
        ResponseModel model = locationService.findCityListById(id);
        return model;
    }
    @RequestMapping("/findCountyListById")
    public ResponseModel findCountyListById(Integer id)throws Exception{
        ResponseModel model = locationService.findCountyListById(id);
        return model;
    }
}
