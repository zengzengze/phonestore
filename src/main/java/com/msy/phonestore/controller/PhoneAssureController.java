package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.PhoneAssure;
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

    @RequestMapping("/updatePhoneAssureMsg")
    public ResponseModel updatePhoneAssureMsg(@RequestBody PhoneAssure phoneAssure)throws Exception{
        ResponseModel model = phoneAssureService.updatePhoneAssureMsg(phoneAssure);
        return model;
    }

    @RequestMapping("/insertPhoneAssureMsg")
    public ResponseModel insertPhoneAssureMsg(@RequestBody PhoneAssure phoneAssure)throws Exception{
        ResponseModel model = phoneAssureService.insertPhoneAssureMsg(phoneAssure);
        return model;
    }

    @RequestMapping("/findPhoneAssureById")
    public ResponseModel findPhoneAssureById(Integer phoneAssureId)throws Exception{
        ResponseModel model = phoneAssureService.findPhoneAssureById(phoneAssureId);
        return model;
    }

    @RequestMapping("/deletePhoneAssureByIds")
    public ResponseModel deletePhoneAssureByIds(@RequestBody Map<String,Integer[]> map)throws Exception{
        ResponseModel model = phoneAssureService.deletePhoneAssureByIds(map.get("ids"));
        return model;
    }
}
