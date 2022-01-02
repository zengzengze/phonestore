package com.msy.phonestore.controller;

import com.msy.phonestore.service.ifc.IPhoneTypeService;
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
 * @Date: 2021/12/25/15:18
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/phoneType")
public class PhoneTypeController {

    @Autowired
    private IPhoneTypeService phoneTypeService;

    @RequestMapping("/PhoneTypeList")
    public ResponseModel findMsgByMap(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = phoneTypeService.findMsgByMap(map);
        return model;
    }
}
