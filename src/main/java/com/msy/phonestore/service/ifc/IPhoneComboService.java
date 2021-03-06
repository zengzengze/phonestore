package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.PhoneCombo;
import com.msy.phonestore.vo.ResponseModel;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/02/16:12
 * @Description:
 */
public interface IPhoneComboService {
    ResponseModel findByMap(Map<String,Object> map) throws Exception;

    ResponseModel findByPhoneComboIds(Integer[] ids) throws Exception;

    ResponseModel findByPhoneComboId(Integer phoneComboId)throws Exception;

    //后台管理
    ResponseModel findComboListPage(Map<String,Object> map)throws Exception;

    ResponseModel insertComboMsg(PhoneCombo phoneCombo)throws Exception;
}
