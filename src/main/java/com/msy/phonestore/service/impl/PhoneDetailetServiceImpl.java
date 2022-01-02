package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.mapper.PhoneDetailetMapper;
import com.msy.phonestore.pojo.Phone;
import com.msy.phonestore.pojo.PhoneDetailet;
import com.msy.phonestore.service.ifc.IPhoneDetailetService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/25/16:31
 * @Description:
 */
@Service
public class PhoneDetailetServiceImpl implements IPhoneDetailetService {

    @Autowired
    private PhoneDetailetMapper phoneDetailetMapper;

    @Override
    public ResponseModel findMsgByIdColorList(Integer id) throws Exception {

        QueryWrapper queryWrapper=new QueryWrapper<>()
                .select("distinct color")
                .eq("phoneId",id);
        return ResponseModel.success(ResCode.SUCCESS,phoneDetailetMapper.selectList(queryWrapper));
    }

    @Override
    public ResponseModel findMsgByIdVersionList(Integer id) throws Exception {

        QueryWrapper queryWrapper=new QueryWrapper<>().select("distinct ram,storage,version").eq("phoneId",id);
        return ResponseModel.success(ResCode.SUCCESS,phoneDetailetMapper.selectList(queryWrapper));
    }

    @Override
    public ResponseModel findMsgByIdColorAndVersion(PhoneDetailet phoneDetailet) throws Exception {
        QueryWrapper queryWrapper=new QueryWrapper<>()
                .select("*")
                .eq("phoneId",phoneDetailet.getPhoneId())
                .eq(phoneDetailet.getColor()!=null && phoneDetailet.getColor()!="","color",phoneDetailet.getColor())
                .eq(phoneDetailet.getRam()!=null && phoneDetailet.getColor()!="","ram",phoneDetailet.getRam())
                .eq(phoneDetailet.getStorage()!=null && phoneDetailet.getColor()!="","storage",phoneDetailet.getStorage())
                .eq(phoneDetailet.getVersion()!=null && phoneDetailet.getColor()!="","version",phoneDetailet.getVersion());

        PhoneDetailet phoneDetailet1 = phoneDetailetMapper.selectOne(queryWrapper);
        if(phoneDetailet==null){
            return ResponseModel.success(ResCode.FAIL);
        }
        return ResponseModel.success(ResCode.SUCCESS,phoneDetailet1);
    }
}
