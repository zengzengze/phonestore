package com.msy.phonestore.controller;

import com.msy.phonestore.service.ifc.IPhoneService;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/24/16:34
 * @Description:
 */
@RestController
@RequestMapping("/phone")
@CrossOrigin
public class PhoneController {

    @Autowired
    private IPhoneService phoneService;

    @RequestMapping("/findMsgByMap")
    public ResponseModel findMsgByMap(@RequestBody Map<String,Object> map)throws Exception{
        System.out.println(map);
        ResponseModel model = phoneService.findMsgByMap(map);
        return model;
    }

    @RequestMapping("/findMsgById")
    public ResponseModel findMsgById(Integer id)throws Exception{
        System.out.println(id);
        ResponseModel model = phoneService.findMsgById(id);
        return model;
    }

    //后台使用

    @RequestMapping("/findPhoneListPageMsg")
    public ResponseModel findPhoneListPageMsg(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = phoneService.findPhoneListPageMsg(map);
        return model;
    }
}
