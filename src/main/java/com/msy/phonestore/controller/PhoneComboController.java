package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.PhoneCombo;
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

    @RequestMapping("/findByPhoneComboId")
    public ResponseModel findByPhoneComboId(Integer phoneComboId)throws Exception{
        ResponseModel model = phoneComboService.findByPhoneComboId(phoneComboId);
        return model;
    }

    //后台使用
    @RequestMapping("/findComboListPage")
    public ResponseModel findComboListPage(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = phoneComboService.findComboListPage(map);
        return model;
    }

    @RequestMapping("/insertComboMsg")
    public ResponseModel insertComboMsg(@RequestBody PhoneCombo phoneCombo)throws Exception{
        ResponseModel model = phoneComboService.insertComboMsg(phoneCombo);
        return model;
    }
}
