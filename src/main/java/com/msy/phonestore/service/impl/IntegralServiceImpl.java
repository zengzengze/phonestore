package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msy.phonestore.mapper.IntegralMapper;
import com.msy.phonestore.pojo.Integral;
import com.msy.phonestore.service.ifc.IntegralService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/12/20:07
 * @Description:
 */
@Service
public class IntegralServiceImpl implements IntegralService {

    @Autowired
    private IntegralMapper integralMapper;

    @Override
    public ResponseModel findIntegralById(Integer userId) throws Exception {
        Integral integral = integralMapper.selectOne(new QueryWrapper<Integral>().eq("userId",userId));
        return ResponseModel.success(ResCode.SUCCESS,integral);
    }
}
