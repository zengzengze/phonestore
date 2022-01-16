package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.OrderDetailet;
import com.msy.phonestore.pojo.Orders;
import com.msy.phonestore.vo.ResponseModel;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/08/10:05
 * @Description:
 */
public interface IOrderDetailetService {

    ResponseModel insertOrderDetailet(OrderDetailet orderDetailet)throws Exception;
}
