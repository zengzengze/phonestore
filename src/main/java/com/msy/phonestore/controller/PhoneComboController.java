package com.msy.phonestore.controller;

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
@RequestMapping("combo")
public class PhoneComboController {
    @Autowired
    private IPhoneComboService phoneComboService;

    @RequestMapping("/comboList")
    public ResponseModel findMsgByMap(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = phoneComboService.findByMap(map);
        return model;
    }
}
