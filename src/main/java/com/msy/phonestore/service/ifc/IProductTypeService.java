package com.msy.phonestore.service.ifc;

import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/30/10:47
 * @Description:
 */
public interface IProductTypeService {

    ResponseModel findProductTypeList(Map<String,Object> map)throws Exception;
}
