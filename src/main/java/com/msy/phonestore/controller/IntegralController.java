package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.Integral;
import com.msy.phonestore.service.ifc.IntegralService;
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
 * @Date: 2022/01/12/20:15
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/integral")
public class IntegralController {
    @Autowired
    private IntegralService integralService;

    @RequestMapping("/findIntegralById")
    public ResponseModel findIntegralById(Integer userId)throws Exception{
//        System.out.println(map);
        ResponseModel model = integralService.findIntegralById(userId);
        return model;
    }
}
