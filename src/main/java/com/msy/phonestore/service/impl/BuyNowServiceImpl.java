package com.msy.phonestore.service.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.PhoneAndPhoneDetailAndComboAndAssureDTO;
import com.msy.phonestore.dto.PhoneDetailAndPhoneAndPhoneTypeDTO;
import com.msy.phonestore.mapper.*;
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
    @Autowired
    private PhoneAssureMapper phoneAssureMapper;
    @Autowired
    private PhoneComboMapper phoneComboMapper;
    @Autowired
    private PhoneDetailMapper phoneDetailMapper;
    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public ResponseModel getfindBuyNowPhoneDetail(Map<String, Object> map) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
                .selectAll(PhoneDetail.class)
                .select(Phone::getPhoneName,Phone::getPhoneImg)
                .select(PhoneType::getPhoneType)
                .innerJoin(Phone.class, Phone::getPhoneId, PhoneDetail::getPhoneId)
                .innerJoin(PhoneType.class,PhoneType::getPhoneTypeId,Phone::getPhoneTypeId)
                .eq(map.get("phoneDetailId")!=null, PhoneDetail::getPhoneDetailId,map.get("phoneDetailId"));

        PhoneDetailAndPhoneAndPhoneTypeDTO DTO = phoneDetailMapper.selectJoinOne(PhoneDetailAndPhoneAndPhoneTypeDTO.class, mpjLambdaWrapper);

        if(map.get("assureId")!=""&&map.get("assureId")!=null){
            PhoneAssure phoneAssure = phoneAssureMapper.selectById(Integer.parseInt(map.get("assureId").toString()) );
            DTO.setPhoneAssure(phoneAssure);
        }
        if(map.get("comboId")!=""&&map.get("comboId")!=null){
            PhoneCombo phoneCombo = phoneComboMapper.selectById(Integer.parseInt(map.get("comboId").toString()) );
            if(phoneCombo.getCommodityId()!=null&& phoneCombo.getCommodityId()!=0){
                Commodity commodity = commodityMapper.selectById(phoneCombo.getCommodityId());
                phoneCombo.setCommodity(commodity);
            }
            DTO.setPhoneCombo(phoneCombo);
        }
        return ResponseModel.success(ResCode.SUCCESS,DTO);
    }
}
