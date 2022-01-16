package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.Cart;
import com.msy.phonestore.vo.ResponseModel;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/24/15:50
 * @Description:
 */
public interface ICartService {
    ResponseModel findMsgByID(Integer userId)throws Exception;

    ResponseModel findMsgByIds(Integer[] ids)throws Exception;

    ResponseModel insertCartMsg(Cart cart)throws Exception;

    ResponseModel updateCartMsg(Cart cart) throws  Exception;

    //购物车初始时是否选中
    ResponseModel updateCartMsgByIDs(Integer[] ids) throws  Exception;

    ResponseModel deleteCartById(Integer[] ids)throws Exception;

    ResponseModel total(Integer[] ids)throws Exception;
}
