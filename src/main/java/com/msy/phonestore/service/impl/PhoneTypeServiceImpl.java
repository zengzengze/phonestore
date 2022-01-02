package com.msy.phonestore.service.impl;

import com.msy.phonestore.mapper.PhoneTypeMapper;
import com.msy.phonestore.pojo.PhoneType;
import com.msy.phonestore.service.ifc.IPhoneTypeService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/12/25/15:13
 * @Description:
 */
@Service
public class PhoneTypeServiceImpl implements IPhoneTypeService {

    @Autowired
    private PhoneTypeMapper phoneTypeMapper;

    @Override
    public ResponseModel findMsgByMap(Map<String, Object> map) throws Exception {
        List<PhoneType> phoneTypes = phoneTypeMapper.selectByMap(map);
        return ResponseModel.success(ResCode.SUCCESS,phoneTypes);
    }
}
