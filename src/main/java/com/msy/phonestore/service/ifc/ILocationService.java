package com.msy.phonestore.service.ifc;

import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/05/9:58
 * @Description:
 */
public interface ILocationService {
    ResponseModel findProvinceList(Integer id) throws Exception;

    ResponseModel findCityListById(Integer id)throws Exception;

    ResponseModel findCountyListById(Integer id)throws Exception;
}
