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

    @RequestMapping("/findOrderPageList")
    public ResponseModel findOrderPageList(@RequestBody Map<String,Object> map)throws Exception{
        System.out.println(map);
        ResponseModel model = orderService.findOrderPageList(map);
        return model;
    }

    @RequestMapping("/findOrderOneByMap")
    public ResponseModel findOrderOneByMap(@RequestBody Map<String,Object> map)throws Exception{
        System.out.println(map);
        ResponseModel model = orderService.findOrderOneByMap(map);
        return model;
    }

    @RequestMapping("/findOrderListByMap")
    public ResponseModel findOrderListByMap(@RequestBody Map<String,Object> map)throws Exception{
        System.out.println(map);
        ResponseModel model = orderService.findOrderListByMap(map);
        return model;
    }

    @RequestMapping("/insertOrder")
    public ResponseModel insertOrder(@RequestBody Map<String,Object> map) throws Exception{
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
    public ResponseModel paymentMsg(@RequestBody Map<String,Object> map) throws Exception{
        ResponseModel model = orderService.paymentMsg(map);
        return model;
    }

    @RequestMapping("/confirmReceiptGoods")
    public ResponseModel confirmReceiptGoods(@RequestBody Map<String,Object> map) throws Exception{
        ResponseModel model = orderService.confirmReceiptGoods(map);
        return model;
    }


    @RequestMapping("/cancelOrderMsg")
    public ResponseModel cancelOrderMsg(@RequestBody Map<String,Object> map) throws Exception{
        ResponseModel model = orderService.cancelOrderMsg(map);
        return model;
    }

    //后台方法
    @RequestMapping("/findOrderListPageMsg")
    public ResponseModel findOrderListPageMsg(@RequestBody Map<String,Object> map) throws Exception{
        ResponseModel model = orderService.findOrderListPageMsg(map);
        return model;
    }

    @RequestMapping("/confirmDeliveryMsg")
    public ResponseModel confirmDeliveryMsg(@RequestBody Map<String,Object> map) throws Exception{
        ResponseModel model = orderService.confirmDeliveryMsg(map);
        return model;
    }

}
