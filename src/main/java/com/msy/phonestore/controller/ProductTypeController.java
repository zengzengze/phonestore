package com.msy.phonestore.controller;

import com.msy.phonestore.service.ifc.IProductTypeService;
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
 * @Date: 2022/01/30/10:50
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/productType")
public class ProductTypeController {

    @Autowired
    private IProductTypeService productTypeService;

    @RequestMapping("/findProductTypeList")
    public ResponseModel findProductTypeList(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = productTypeService.findProductTypeList(map);
        return model;
    }
}
