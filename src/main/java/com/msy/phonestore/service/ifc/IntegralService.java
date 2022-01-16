package com.msy.phonestore.service.ifc;
import com.msy.phonestore.vo.ResponseModel;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/12/20:06
 * @Description:
 */
public interface IntegralService {

    ResponseModel findIntegralById(Integer userId)throws Exception;
}
