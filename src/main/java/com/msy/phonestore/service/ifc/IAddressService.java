package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.Address;
import com.msy.phonestore.pojo.Cart;
import com.msy.phonestore.vo.ResponseModel;
import org.apache.tomcat.util.security.Escape;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/12/30/15:06
 * @Description:
 */
public interface IAddressService {
    ResponseModel findMsgByMap(Map<String,Object> map)throws Exception;


    ResponseModel updateAddressMsg(Address address) throws  Exception;

    //修改默认地址
    ResponseModel setDefault(Address address)throws Exception;

    //删除功能
    ResponseModel delAddressMsgByIds(Integer[] ids)throws Exception;

}
