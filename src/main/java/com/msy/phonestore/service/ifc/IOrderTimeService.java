package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.OrderTime;
import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/16/11:03
 * @Description:
 */
public interface IOrderTimeService {

    ResponseModel insertOrderTimeMsg(OrderTime orderTime)throws Exception;

    ResponseModel findOrderTimeMsg(Map<String,Object> map)throws Exception;
}
