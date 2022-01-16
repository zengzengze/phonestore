package com.msy.phonestore.service.impl;

import com.msy.phonestore.mapper.PhoneComboMapper;
import com.msy.phonestore.pojo.PhoneCombo;
import com.msy.phonestore.service.ifc.IPhoneComboService;
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
 * @Date: 2022/01/02/16:16
 * @Description:
 */
@Service
public class PhoneComboServiceImpl implements IPhoneComboService {

    @Autowired
    private PhoneComboMapper phoneComboMapper;
    @Override
    public ResponseModel findByMap(Map<String, Object> map) throws Exception {
        List<PhoneCombo> phoneCombos = phoneComboMapper.selectByMap(map);
        return ResponseModel.success(ResCode.SUCCESS,phoneCombos);
    }

    @Override
    public ResponseModel findById(Integer[] ids) throws Exception {
        return null;
    }
}
