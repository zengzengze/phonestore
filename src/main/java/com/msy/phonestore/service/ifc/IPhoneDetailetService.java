package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.PhoneDetailet;
import com.msy.phonestore.vo.ResponseModel;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/25/16:30
 * @Description:
 */
public interface IPhoneDetailetService {

    //根据手机id查询该手机所有颜色
    ResponseModel findMsgByIdColorList(Integer id)throws Exception;

    //根据手机id查询该手机所有版本
    ResponseModel findMsgByIdVersionList(Integer id)throws Exception;

    //根据手机id和颜色及版本查询该手机所有版本
    ResponseModel findMsgByIdColorAndVersion(PhoneDetailet phoneDetailet)throws Exception;

}
