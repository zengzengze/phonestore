package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.OrderDetail;
import com.msy.phonestore.vo.ResponseModel;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/08/10:05
 * @Description:
 */
public interface IOrderDetailService {

    ResponseModel insertOrderDetail(OrderDetail orderDetail)throws Exception;
}
