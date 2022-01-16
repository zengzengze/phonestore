package com.msy.phonestore.service.impl;

import com.msy.phonestore.mapper.OrderDetailetMapper;
import com.msy.phonestore.pojo.OrderDetailet;
import com.msy.phonestore.service.ifc.IOrderDetailetService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/08/10:06
 * @Description:
 */
@Service
public class OrderDetailetServiceImpl implements IOrderDetailetService {
    @Autowired
    private OrderDetailetMapper orderDetailetMapper;

    @Override
    public ResponseModel insertOrderDetailet(OrderDetailet orderDetailet) throws Exception {
        int row = orderDetailetMapper.insert(orderDetailet);

        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}
