package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.OrderTime;
import com.msy.phonestore.pojo.Orders;
import com.msy.phonestore.service.ifc.IOrderService;
import com.msy.phonestore.service.ifc.IOrderTimeService;
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
@RequestMapping("/orderTime")
public class OrderTimeController {
    @Autowired
    private IOrderTimeService orderTimeService;

    @RequestMapping("/insertOrderTimeMsg")
    public ResponseModel insertOrderTimeMsg(@RequestBody OrderTime orderTime)throws Exception{
        ResponseModel model = orderTimeService.insertOrderTimeMsg(orderTime);
        return model;
    }

    @RequestMapping("/findOrderTimeMsg")
    public ResponseModel findOrderTimeMsg(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = orderTimeService.findOrderTimeMsg(map);
        return model;
    }

//    @RequestMapping("/insertOrder")
//    public ResponseModel insertOrder(@RequestBody Map<String,Object> map) throws Exception{
////        System.out.println(map.get("order"));
//        ResponseModel model = orderService.insertOrder(map);
//        return model;
//    }
//
//    @RequestMapping("/updateOrder")
//    public ResponseModel updateOrder(@RequestBody Orders order) throws Exception{
//        ResponseModel model = orderService.updateOrder(order);
//        return model;
//    }
//
//    @RequestMapping("/deleteOrder")
//    public ResponseModel deleteOrder(@RequestBody Map<String, Object[]> map) throws Exception{
//        ResponseModel model = orderService.deleteOrder(map.get("ids"));
//        return model;
//    }
//
//    @RequestMapping("/paymentMsg")
//    public ResponseModel paymentMsg(@RequestBody Orders order) throws Exception{
//        ResponseModel model = orderService.paymentMsg(order);
//        return model;
//    }
}
