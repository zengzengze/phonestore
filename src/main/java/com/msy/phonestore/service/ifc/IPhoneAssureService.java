package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.PhoneAssure;
import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/02/16:16
 * @Description:
 */
public interface IPhoneAssureService {
    ResponseModel findByMap(Map<String,Object> map) throws Exception;

    ResponseModel findById(Integer[] ids) throws Exception;

    //手机保障
    ResponseModel findAssureListPage(Map<String,Object> map)throws Exception;
}
