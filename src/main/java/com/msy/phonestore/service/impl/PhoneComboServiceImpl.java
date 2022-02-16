package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.PhoneAssureAndPhoneAndPhoneTypeDTO;
import com.msy.phonestore.dto.PhoneComboAndPhoneAndPhoneTypeDTO;
import com.msy.phonestore.mapper.PhoneComboMapper;
import com.msy.phonestore.pojo.*;
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
    public ResponseModel findByPhoneComboIds(Integer[] ids) throws Exception {
        return null;
    }

    @Override
    public ResponseModel findByPhoneComboId(Integer phoneComboId) throws Exception {
        PhoneCombo phoneCombo = phoneComboMapper.selectById(phoneComboId);

        return ResponseModel.success(ResCode.SUCCESS,phoneCombo);
    }


    @Override
    public ResponseModel findComboListPage(Map<String, Object> map) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<PhoneCombo>()
                .selectAll(PhoneCombo.class)
                .select(Phone::getPhoneName,Phone::getPhoneImg)
                .select(PhoneType::getPhoneType)
                .innerJoin(Phone.class,Phone::getPhoneId,PhoneCombo::getPhoneId)
                .innerJoin(PhoneType.class,PhoneType::getPhoneTypeId,Phone::getPhoneTypeId);

        Page comboPage=new Page<>((Integer)map.get("pageNumber"),(Integer)map.get("pageSize"));

        IPage<PhoneComboAndPhoneAndPhoneTypeDTO> pageDTOs = phoneComboMapper.selectJoinPage(comboPage, PhoneComboAndPhoneAndPhoneTypeDTO.class, mpjLambdaWrapper);
        return ResponseModel.success(ResCode.SUCCESS,pageDTOs);
    }

    @Override
    public ResponseModel insertComboMsg(PhoneCombo phoneCombo) throws Exception {
        int row = phoneComboMapper.insert(phoneCombo);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}
