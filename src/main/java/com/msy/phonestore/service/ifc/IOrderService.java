package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.Orders;
import com.msy.phonestore.vo.ResponseModel;

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

    ResponseModel paymentMsg(Orders order)throws Exception;

}
