package com.msy.phonestore.service.ifc;

import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/12/25/15:14
 * @Description:
 */
public interface IPhoneTypeService {
    //查询手机类型
    ResponseModel findMsgByMap(Map<String,Object> map)throws Exception;
}
