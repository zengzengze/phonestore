package com.msy.phonestore.controller;

import com.msy.phonestore.service.ifc.IDeliveryToolsInfoService;
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
 * @Date: 2022/01/13/21:03
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private IDeliveryToolsInfoService deliveryToolsInfoService;

    @RequestMapping("/findDeliveryTools")
    public ResponseModel deliveryTools(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = deliveryToolsInfoService.findDeliveryMsg(map);
        return model;
    }

}
