package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.PhoneDetail;
import com.msy.phonestore.service.ifc.IPhoneDetailService;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/25/16:37
 * @Description:
 */
@RestController
@RequestMapping("/phoneDetail")
public class PhoneDetailController {

    @Autowired
    private IPhoneDetailService phoneDetailService;

    @RequestMapping("/findMsgByIdColorList")
    public ResponseModel findMsgByIdColorList(Integer id)throws Exception{
        System.out.println(id);
        ResponseModel model = phoneDetailService.findMsgByIdColorList(id);
        return model;
    }

    @RequestMapping("/findMsgByIdVersionList")
    public ResponseModel findMsgByIdVersionList(Integer id)throws Exception{
        System.out.println(id);
        ResponseModel model = phoneDetailService.findMsgByIdVersionList(id);
        return model;
    }
    @RequestMapping("/findMsgByIdColorAndVersion")
    public ResponseModel findMsgByIdColorAndVersion(@RequestBody PhoneDetail phoneDetail)throws Exception{
        System.out.println(phoneDetail);
        ResponseModel model = phoneDetailService.findMsgByIdColorAndVersion(phoneDetail);
        return model;
    }

    @RequestMapping("/findByPhoneDetailId")
    public ResponseModel findByPhoneDetailId( Integer phoneDetailId)throws Exception{
        ResponseModel model = phoneDetailService.findByPhoneDetailId(phoneDetailId);
        return model;
    }

    //后台使用
    @RequestMapping("/findByPhoneIdListMsg")
    public ResponseModel findByPhoneIdListMsg(Integer phoneId)throws Exception{
        ResponseModel model = phoneDetailService.findByPhoneIdListMsg(phoneId);
        return model;
    }

    @RequestMapping("/insertPhoneDetailByMap")
    public ResponseModel insertPhoneDetailByMap(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = phoneDetailService.insertPhoneDetailByMap(map);
        return model;
    }

    @RequestMapping("/updatePhoneDetailMsg")
    public ResponseModel updatePhoneDetailMsg(@RequestBody PhoneDetail phoneDetail)throws Exception{
        ResponseModel model = phoneDetailService.updatePhoneDetailMsg(phoneDetail);
        return model;
    }

}
