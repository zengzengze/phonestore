package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.Orders;
import com.msy.phonestore.service.ifc.IOrderService;
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
 * @Date: 2022/01/07/17:34
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/orderDetailet")
public class OrderDetailetController {
    @Autowired
    private IOrderService orderService;
//
//    @RequestMapping("/findOrdersList")
//    public ResponseModel findOrdersList(@RequestBody Map<String,Object> map)throws Exception{
//        ResponseModel model = orderService.findOrdersAndOrderDetailet(map);
//        return model;
//    }

    @RequestMapping("/insertOrderDetailet")
    public ResponseModel insertOrder(@RequestBody Map<String,Object[]> map) throws Exception{
//        System.out.println(map.get("orderDetailet"));
//        ResponseModel model = orderService.insertOrder(map);
        return null;
    }

//    @RequestMapping("/updateOrder")
//    public ResponseModel updateOrder(@RequestBody Orders order) throws Exception{
//        ResponseModel model = orderService.updateOrder(order);
//        return model;
//    }
//
//    @RequestMapping("/deleteOrder")
//    public ResponseModel deleteOrder(@RequestBody Map<String, Integer[]> map) throws Exception{
//        ResponseModel model = orderService.deleteOrder(map.get("ids"));
//        return model;
//    }
}
