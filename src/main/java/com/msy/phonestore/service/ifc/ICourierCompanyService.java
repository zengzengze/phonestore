package com.msy.phonestore.service.ifc;

import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/20/15:39
 * @Description:
 */
public interface ICourierCompanyService {
    ResponseModel findCourierCompanyList(Map<String,Object> map)throws Exception;
}
