package com.msy.phonestore.controller;

import com.msy.phonestore.service.ifc.IParcelService;
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
 * @Date: 2022/01/13/22:57
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/parcel")
public class ParcelController {

    @Autowired
    private IParcelService parcelService;

    @RequestMapping("/findParcelByMap")
    public ResponseModel findMsgByMap(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = parcelService.findParcelByMap(map);
        return model;
    }
}
