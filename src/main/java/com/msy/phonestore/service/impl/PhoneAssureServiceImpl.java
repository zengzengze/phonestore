package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.PhoneAssureAndPhoneAndPhoneTypeDTO;
import com.msy.phonestore.mapper.PhoneAssureMapper;
import com.msy.phonestore.pojo.Phone;
import com.msy.phonestore.pojo.PhoneAssure;
import com.msy.phonestore.pojo.PhoneType;
import com.msy.phonestore.service.ifc.IPhoneAssureService;
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
 * @Date: 2022/01/02/16:17
 * @Description:
 */
@Service
public class PhoneAssureServiceImpl implements IPhoneAssureService {

    @Autowired
    private PhoneAssureMapper phoneAssureMapper;

    @Override
    public ResponseModel findByMap(Map<String, Object> map) throws Exception {
        List<PhoneAssure> phoneAssures = phoneAssureMapper.selectByMap(map);
        return ResponseModel.success(ResCode.SUCCESS,phoneAssures);
    }
    @Override
    public ResponseModel findById(Integer[] ids) throws Exception {
        return null;
    }

    @Override
    public ResponseModel findAssureListPage(Map<String, Object> map) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<PhoneAssure>()
                .selectAll(PhoneAssure.class)
                .select(Phone::getPhoneName,Phone::getPhoneImg)
                .select(PhoneType::getPhoneType)
                .innerJoin(Phone.class,Phone::getPhoneId,PhoneAssure::getPhoneId)
                .innerJoin(PhoneType.class,PhoneType::getPhoneTypeId,Phone::getPhoneTypeId);
        Page assurePage=new Page<>((Integer)map.get("pageNumber"),(Integer)map.get("pageSize"));

        IPage<PhoneAssureAndPhoneAndPhoneTypeDTO> pageDTOs = phoneAssureMapper.selectJoinPage(assurePage, PhoneAssureAndPhoneAndPhoneTypeDTO.class, mpjLambdaWrapper);
        return ResponseModel.success(ResCode.SUCCESS,pageDTOs);
    }
}
