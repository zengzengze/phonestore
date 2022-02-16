package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.Cart;
import com.msy.phonestore.pojo.PhoneImg;
import com.msy.phonestore.service.ifc.ICartService;
import com.msy.phonestore.service.ifc.IPhoneImgService;
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
@RequestMapping("/phoneImg")
public class PhoneImgController {

    @Autowired
    private IPhoneImgService phoneImgService;


    @RequestMapping("/findPhoneImgMsg")
    public ResponseModel findPhoneImgMsg(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = phoneImgService.findPhoneImgMsg(map);
        return model;
    }

    @RequestMapping("/updatePhoneImgByMapMsg")
    public ResponseModel updatePhoneImgByMapMsg(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = phoneImgService.updatePhoneImgByMapMsg(map);
        return model;
    }

}
