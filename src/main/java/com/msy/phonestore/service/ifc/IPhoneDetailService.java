package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.PhoneDetail;
import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/25/16:30
 * @Description:
 */
public interface IPhoneDetailService {

    //根据手机id查询该手机所有颜色
    ResponseModel findMsgByIdColorList(Integer phoneId)throws Exception;

    //根据手机id查询该手机所有版本
    ResponseModel findMsgByIdVersionList(Integer phoneId)throws Exception;

    //根据手机id和颜色及版本查询该手机所有版本
    ResponseModel findMsgByIdColorAndVersion(PhoneDetail phoneDetail)throws Exception;

    ResponseModel findByPhoneDetailId(Integer phoneDetailId)throws Exception;

    //后台使用
    ResponseModel findByPhoneIdListMsg(Integer phoneId)throws Exception;

    ResponseModel insertPhoneDetailByMap(Map<String,Object> map)throws Exception;

    ResponseModel updatePhoneDetailMsg(PhoneDetail phoneDetail)throws Exception;

}
