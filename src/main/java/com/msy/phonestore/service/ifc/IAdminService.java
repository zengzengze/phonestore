package com.msy.phonestore.service.ifc;

import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/09/16:50
 * @Description:
 */
public interface IAdminService {
    ResponseModel backLogin(Map<String,Object> map)throws Exception;
}
