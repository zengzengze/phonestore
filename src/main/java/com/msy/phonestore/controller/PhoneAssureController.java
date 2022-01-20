package com.msy.phonestore.controller;

import com.msy.phonestore.service.ifc.IPhoneAssureService;
import com.msy.phonestore.service.ifc.IPhoneComboService;
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
 * @Date: 2022/01/02/16:19
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("assure")
public class PhoneAssureController {
    @Autowired
    private IPhoneAssureService phoneAssureService;

    @RequestMapping("/assureList")
    public ResponseModel findMsgByMap(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = phoneAssureService.findByMap(map);
        return model;
    }

    //后台使用
    @RequestMapping("/findAssureListPage")
    public ResponseModel findAssureListPage(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = phoneAssureService.findAssureListPage(map);
        return model;
    }
}
