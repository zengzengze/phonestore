package com.msy.phonestore.service.ifc;

import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/13/22:55
 * @Description:
 */
public interface IParcelService {

    ResponseModel findParcelByMap(Map<String,Object> map)throws Exception;
}
