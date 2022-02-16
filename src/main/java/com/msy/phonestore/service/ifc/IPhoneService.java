package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.Phone;
import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/24/15:50
 * @Description:
 */
public interface IPhoneService {
    ResponseModel findMsgByMap(Map<String,Object> map)throws Exception;

    //根据手机id查询手机
    ResponseModel findMsgById(Integer id)throws Exception;

    //后台使用
    ResponseModel findPhoneListPageMsg(Map<String,Object> map)throws Exception;

    ResponseModel insertPhoneMsg(Map<String,Object> map)throws Exception;

    ResponseModel findByPhoneIdMsg(Integer phoneId)throws Exception;

}
