package com.msy.phonestore.service.impl;

import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.PhoneAndPhoneDetailetAndComboAndAssureDTO;
import com.msy.phonestore.mapper.PhoneMapper;
import com.msy.phonestore.pojo.*;
import com.msy.phonestore.service.ifc.IBuyNowService;
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
 * @Date: 2022/01/13/17:07
 * @Description:
 */
@Service
public class BuyNowServiceImpl implements IBuyNowService {

    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    public ResponseModel findByMapMsg(Map<String, Object> map) throws Exception {

        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
                .selectAll(PhoneDetailet.class)
                .select(Phone::getPhoneName,Phone::getPhoneImg)
                .select(PhoneCombo::getCombo,PhoneCombo::getComboId,PhoneCombo::getComboPrice)
                .select(PhoneAssure::getAssureId,PhoneAssure::getAssure,PhoneAssure::getAssureImg,PhoneAssure::getAssurePrice)
                .innerJoin(PhoneDetailet.class, PhoneDetailet::getPhoneId, Phone::getPhoneId)
                .innerJoin(PhoneCombo.class,PhoneCombo::getPhoneId,Phone::getPhoneId)
                .innerJoin(PhoneAssure.class,PhoneAssure::getPhoneId,Phone::getPhoneId)
                .eq(map.get("phoneDetailetId")!=null,PhoneDetailet::getPhoneDetailetId,map.get("phoneDetailetId"))
                .eq(map.get("comboId")!=null,PhoneCombo::getComboId,map.get("comboId"))
                .eq(map.get("assureId")!=null,PhoneAssure::getAssureId,map.get("assureId"));

        List<PhoneAndPhoneDetailetAndComboAndAssureDTO> DTOS = phoneMapper.selectJoinList(PhoneAndPhoneDetailetAndComboAndAssureDTO.class, mpjLambdaWrapper);
        return ResponseModel.success(ResCode.SUCCESS,DTOS);
    }
}
