package com.msy.phonestore.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.PhoneDetailAndPhoneAndPhoneTypeDTO;
import com.msy.phonestore.mapper.PhoneDetailMapper;
import com.msy.phonestore.mapper.PhoneImgMapper;
import com.msy.phonestore.pojo.Phone;
import com.msy.phonestore.pojo.PhoneDetail;
import com.msy.phonestore.pojo.PhoneImg;
import com.msy.phonestore.pojo.PhoneType;
import com.msy.phonestore.service.ifc.IPhoneDetailService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/25/16:31
 * @Description:
 */
@Service
public class PhoneDetailServiceImpl implements IPhoneDetailService {

    @Autowired
    private PhoneDetailMapper phoneDetailMapper;
    @Autowired
    private PhoneImgMapper phoneImgMapper;

    @Override
    public ResponseModel findMsgByIdColorList(Integer phoneId) throws Exception {

        QueryWrapper queryWrapper=new QueryWrapper<>()
                .select("distinct color")
                .eq("phoneId",phoneId);
        return ResponseModel.success(ResCode.SUCCESS,phoneDetailMapper.selectList(queryWrapper));
    }

    @Override
    public ResponseModel findMsgByIdVersionList(Integer phoneId) throws Exception {

        QueryWrapper queryWrapper=new QueryWrapper<>().select("distinct ram,storage,version").eq("phoneId",phoneId);
        return ResponseModel.success(ResCode.SUCCESS,phoneDetailMapper.selectList(queryWrapper));
    }

    @Override
    public ResponseModel findMsgByIdColorAndVersion(PhoneDetail phoneDetail) throws Exception {
        QueryWrapper queryWrapper=new QueryWrapper<>()
                .select("*")
                .eq("phoneId",phoneDetail.getPhoneId())
                .eq(phoneDetail.getColor()!=null && phoneDetail.getColor()!="","color",phoneDetail.getColor())
                .eq(phoneDetail.getRam()!=null && phoneDetail.getColor()!="","ram",phoneDetail.getRam())
                .eq(phoneDetail.getStorage()!=null && phoneDetail.getColor()!="","storage",phoneDetail.getStorage())
                .eq(phoneDetail.getVersion()!=null && phoneDetail.getColor()!="","version",phoneDetail.getVersion());

        PhoneDetail phoneDetail1 = phoneDetailMapper.selectOne(queryWrapper);
        if(phoneDetail==null){
            return ResponseModel.success(ResCode.FAIL);
        }
        return ResponseModel.success(ResCode.SUCCESS,phoneDetail1);
    }

    @Override
    public ResponseModel findByPhoneDetailId(Integer phoneDetailId) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
                .selectAll(PhoneDetail.class)
                .select(Phone::getPhoneName,Phone::getPhoneImg)
                .select(PhoneType::getPhoneType)
                .innerJoin(Phone.class, Phone::getPhoneId, PhoneDetail::getPhoneId)
                .innerJoin(PhoneType.class,PhoneType::getPhoneTypeId,Phone::getPhoneTypeId)
                .eq(phoneDetailId!=null, PhoneDetail::getPhoneDetailId,phoneDetailId);

        PhoneDetailAndPhoneAndPhoneTypeDTO DTO = phoneDetailMapper.selectJoinOne(PhoneDetailAndPhoneAndPhoneTypeDTO.class, mpjLambdaWrapper);
        return ResponseModel.success(ResCode.SUCCESS,DTO);
    }

    //后台使用
    @Override
    public ResponseModel findByPhoneIdListMsg(Integer phoneId) throws Exception {
        List<PhoneDetail> phoneDetailList = phoneDetailMapper.selectList(new QueryWrapper<PhoneDetail>()
                .eq("phoneId", phoneId));
        return ResponseModel.success(ResCode.SUCCESS,phoneDetailList);
    }

    @Override
    @Transactional
    public ResponseModel insertPhoneDetailByMap(Map<String, Object> map) throws Exception {
        PhoneDetail phoneDetail = JSON.parseObject(JSON.toJSONString(map.get("phoneDetail")), PhoneDetail.class);
        int row = phoneDetailMapper.insert(phoneDetail);
        if(row>0){
            PhoneDetail phoneDetail1 = phoneDetailMapper.selectOne(new QueryWrapper<PhoneDetail>()
                    .eq("color", phoneDetail.getColor())
                    .eq("phoneId", phoneDetail.getPhoneId())
                    .eq("ram", phoneDetail.getRam())
                    .eq("storage", phoneDetail.getStorage())
                    .eq("version", phoneDetail.getVersion())
                    .eq("screenSize", phoneDetail.getScreenSize()));

            PhoneImg phoneImg=new PhoneImg();
            phoneImg.setPhoneDetailId(phoneDetail1.getPhoneDetailId());

            phoneImg.setImgOne(phoneDetail.getImgList()[0]);
            phoneImg.setImgTwo(phoneDetail.getImgList()[1]);
            phoneImg.setImgThree(phoneDetail.getImgList()[2]);
            phoneImg.setImgFour(phoneDetail.getImgList()[3]);
            phoneImg.setImgFive(phoneDetail.getImgList()[4]);

            int row1 = phoneImgMapper.insert(phoneImg);
            if(row1>0){
                return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel updatePhoneDetailMsg(PhoneDetail phoneDetail) throws Exception {
        int row = phoneDetailMapper.updateById(phoneDetail);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

}
