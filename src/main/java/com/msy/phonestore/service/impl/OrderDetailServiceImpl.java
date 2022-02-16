package com.msy.phonestore.service.impl;

import com.msy.phonestore.mapper.OrderDetailMapper;
import com.msy.phonestore.pojo.OrderDetail;
import com.msy.phonestore.service.ifc.IOrderDetailService;
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
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public ResponseModel insertOrderDetail(OrderDetail orderDetail) throws Exception {
        int row = orderDetailMapper.insert(orderDetail);

        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}
