package com.msy.phonestore.service.ifc;

import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/30/11:30
 * @Description:
 */
public interface ICommodityService {

    ResponseModel findCommodityListByMap(Map<String,Object> map)throws Exception;

    ResponseModel findCommodityById(Integer commodityId)throws Exception;

    ResponseModel findCommodityByIds(Integer[] ids)throws Exception;
}
