package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.PhoneDetailet;
import com.msy.phonestore.service.ifc.IPhoneDetailetService;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/25/16:37
 * @Description:
 */
@RestController
@RequestMapping("/phoneDetailet")
public class PhoneDetailetController {

    @Autowired
    private IPhoneDetailetService phoneDetailetService;

    @RequestMapping("/findMsgByIdColorList")
    public ResponseModel findMsgByIdColorList(Integer id)throws Exception{
        System.out.println(id);
        ResponseModel model = phoneDetailetService.findMsgByIdColorList(id);
        return model;
    }

    @RequestMapping("/findMsgByIdVersionList")
    public ResponseModel findMsgByIdVersionList(Integer id)throws Exception{
        System.out.println(id);
        ResponseModel model = phoneDetailetService.findMsgByIdVersionList(id);
        return model;
    }
    @RequestMapping("/findMsgByIdColorAndVersion")
    public ResponseModel findMsgByIdColorAndVersion(@RequestBody PhoneDetailet phoneDetailet)throws Exception{
        System.out.println(phoneDetailet);
        ResponseModel model = phoneDetailetService.findMsgByIdColorAndVersion(phoneDetailet);
        return model;
    }

}
