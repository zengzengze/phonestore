package com.msy.phonestore.service.ifc;

import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/13/21:33
 * @Description:
 */
public interface IDeliveryToolsInfoService {
    ResponseModel findDeliveryMsg(Map<String,Object> map)throws Exception;
}
