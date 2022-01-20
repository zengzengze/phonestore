package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msy.phonestore.mapper.OrderTimeMapper;
import com.msy.phonestore.pojo.OrderTime;
import com.msy.phonestore.service.ifc.IOrderTimeService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/16/11:04
 * @Description:
 */
@Service
public class OrderTimeServiceImpl implements IOrderTimeService {

    @Autowired
    private OrderTimeMapper orderTimeMapper;


    @Override
    public ResponseModel findOrderTimeMsg(Map<String, Object> map) throws Exception {
        List<OrderTime> orderTimes = orderTimeMapper.selectList(new QueryWrapper<OrderTime>()
        .eq(map.get("orderId")!=null,"orderId",map.get("orderId"))
        .eq(map.get("orderTimeId")!=null,"orderTimeId",map.get("orderTimeId"))
        .orderByDesc("orderTime"));
        return ResponseModel.success(ResCode.SUCCESS,orderTimes);
    }
}
