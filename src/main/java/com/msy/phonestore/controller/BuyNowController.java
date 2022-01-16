package com.msy.phonestore.controller;

import com.msy.phonestore.service.ifc.IBuyNowService;
import com.msy.phonestore.service.ifc.ICartService;
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
 * @Date: 2022/01/13/17:17
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/buyNow")
public class BuyNowController {

    @Autowired
    private IBuyNowService buyNowService;

    @RequestMapping("/findByMapMsg")
    public ResponseModel findByMapMsg(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = buyNowService.findByMapMsg(map);
        return model;
    }
}
