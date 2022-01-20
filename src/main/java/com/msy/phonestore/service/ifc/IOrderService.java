package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.Orders;
import com.msy.phonestore.vo.ResponseModel;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/07/16:41
 * @Description:
 */
public interface IOrderService {

    ResponseModel findOrdersAndOrderDetailet(Map<String,Object> map)throws Exception;

    ResponseModel insertOrder(Map<String,Object> map)throws Exception;

    ResponseModel updateOrder(Orders order)throws Exception;

    ResponseModel deleteOrder(Object[] ids)throws Exception;

    //支付方法
    ResponseModel paymentMsg(Map<String,Object> map)throws Exception;
    //确认收货方法
    ResponseModel confirmReceiptGoods(Map<String,Object> map)throws Exception;
    //取消订单方法
    ResponseModel cancelOrderMsg(Map<String,Object> map)throws Exception;


    //后台使用方法
    ResponseModel findOrderListPageMsg(Map<String,Object> map)throws Exception;

    //确认发货
    ResponseModel confirmDeliveryMsg(Map<String,Object> map)throws Exception;
}
