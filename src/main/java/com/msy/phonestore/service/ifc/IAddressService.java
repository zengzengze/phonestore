package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.Address;
import com.msy.phonestore.pojo.Cart;
import com.msy.phonestore.pojo.Integral;
import com.msy.phonestore.vo.ResponseModel;
import org.apache.tomcat.util.security.Escape;
import org.omg.PortableInterceptor.INACTIVE;

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

    ResponseModel findMsgById(Integer id)throws Exception;

    //选择收货地址
    ResponseModel updateAddressMsg(Address address) throws  Exception;

    ResponseModel insertAddressMsg(Address address) throws Exception;

    //修改地址
    ResponseModel updateByAddressMsg(Address address)throws Exception;

    //修改默认地址
    ResponseModel setDefault(Address address)throws Exception;

    //删除功能
    ResponseModel delAddressMsgByIds(Integer[] ids)throws Exception;

    //页面再次刷新时修改地址状态
    ResponseModel updateAddressState(Integer userId)throws Exception;

    //保存并使用用到
//    ResponseModel findMaxAddressMsg(Integer userId)throws Exception;

}
