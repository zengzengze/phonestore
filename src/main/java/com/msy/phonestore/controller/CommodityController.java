package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.Integral;
import com.msy.phonestore.service.ifc.ICommodityService;
import com.msy.phonestore.service.ifc.IProductTypeService;
import com.msy.phonestore.vo.FileStreamUtils;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private ICommodityService commodityService;

    @RequestMapping("/findCommodityListByMap")
    public ResponseModel findCommodityListByMap(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = commodityService.findCommodityListByMap(map);
        return model;
    }

    @RequestMapping("/findCommodityById")
    public ResponseModel findCommodityById(Integer commodityId)throws Exception{
        ResponseModel model = commodityService.findCommodityById(commodityId);
        return model;
    }

    @RequestMapping("/findCommodityByIds")
    public ResponseModel findCommodityByIds(@RequestBody Map<String, Integer[]> map)throws Exception{
        ResponseModel model = commodityService.findCommodityByIds(map.get("ids"));
        return model;
    }


}
