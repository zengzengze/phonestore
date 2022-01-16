package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.Orders;
import com.msy.phonestore.service.ifc.IOrderService;
import com.msy.phonestore.vo.ResponseModel;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/findOrdersList")
    public ResponseModel findOrdersList(@RequestBody Map<String,Object> map)throws Exception{
        System.out.println(map);
        ResponseModel model = orderService.findOrdersAndOrderDetailet(map);
        return model;
    }

    @RequestMapping("/insertOrder")
    public ResponseModel insertOrder(@RequestBody Map<String,Object> map) throws Exception{
//        System.out.println(map.get("order"));
        ResponseModel model = orderService.insertOrder(map);
        return model;
    }

    @RequestMapping("/updateOrder")
    public ResponseModel updateOrder(@RequestBody Orders order) throws Exception{
        ResponseModel model = orderService.updateOrder(order);
        return model;
    }

    @RequestMapping("/deleteOrder")
    public ResponseModel deleteOrder(@RequestBody Map<String, Object[]> map) throws Exception{
        ResponseModel model = orderService.deleteOrder(map.get("ids"));
        return model;
    }

    @RequestMapping("/paymentMsg")
    public ResponseModel paymentMsg(@RequestBody Orders order) throws Exception{
        ResponseModel model = orderService.paymentMsg(order);
        return model;
    }
}
