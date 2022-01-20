package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.PhoneAndPhoneDetailetAndphoneTypeDTO;
import com.msy.phonestore.dto.PhoneAndTypeAndDetailetAndImgDTO;
import com.msy.phonestore.mapper.PhoneMapper;
import com.msy.phonestore.pojo.Phone;
import com.msy.phonestore.pojo.PhoneDetailet;
import com.msy.phonestore.pojo.PhoneType;
import com.msy.phonestore.service.ifc.IPhoneService;
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
 * @Date: 2021/11/24/15:50
 * @Description:
 */
@Service
public class PhoneServiceImpl implements IPhoneService {

    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    public ResponseModel findMsgByMap(Map<String, Object> map) throws Exception {
//        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
//                .selectAll(Phone.class)
//                .select(PhoneType::getPType)
//                .select(PhoneDetailet::getPDetailetId,PhoneDetailet::getRam,PhoneDetailet::getStorage,PhoneDetailet::getVersion,
//                        PhoneDetailet::getColor,PhoneDetailet::getPrice,PhoneDetailet::getScreenSize,
//                        PhoneDetailet::getQuantity)
//                .innerJoin(PhoneDetailet.class,PhoneDetailet::getPhoneId,Phone::getPhoneId)
//                .innerJoin(PhoneType.class, PhoneType::getPTypeId,Phone::getPTypeId)
//                .eq(map.get("pTypeId")!=null,PhoneType::getPTypeId,map.get("pTypeId"));
        MPJQueryWrapper queryWrapper=new MPJQueryWrapper<>()
                .select("t.*")
                .select("pt.phoneType")
                .select("pd.phoneDetailetId,pd.Ram,pd.Storage,pd.Version,pd.Color,pd.Price,pd.ScreenSize")
                .innerJoin("Phone_Type pt on t.phoneTypeId=pt.phoneTypeId")
                .innerJoin("Phone_Detailet pd on t.PhoneId = pd.PhoneId")
                .eq(map.get("phoneTypeId")!=null,"t.phoneTypeId",map.get("phoneTypeId"));
        Page phonePage=new Page<>((Integer)map.get("pageNumber"),(Integer)map.get("pageSize"));
        IPage<PhoneAndTypeAndDetailetAndImgDTO> phoneIPage = phoneMapper.selectJoinPage(phonePage, PhoneAndTypeAndDetailetAndImgDTO.class,queryWrapper);

//        System.out.println("总页数： "+phoneIPage.getPages());
//        System.out.println("总记录数： "+phoneIPage.getTotal());
//        phoneIPage.getRecords().forEach(System.out::println);
        return ResponseModel.success(ResCode.SUCCESS,phoneIPage);
    }
    @Override
    public ResponseModel findMsgById(Integer id) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
                .select(Phone::getPhoneId,Phone::getPhoneName,Phone::getPhoneImg,Phone::getRemark,Phone::getPraise)
                .select(PhoneType::getPhoneType)
                .select(PhoneDetailet::getPhoneDetailetId,PhoneDetailet::getRam,PhoneDetailet::getStorage,PhoneDetailet::getVersion,
                        PhoneDetailet::getColor,PhoneDetailet::getPrice,PhoneDetailet::getScreenSize,
                        PhoneDetailet::getQuantity)
                .innerJoin(PhoneDetailet.class,PhoneDetailet::getPhoneId,Phone::getPhoneId)
                .innerJoin(PhoneType.class, PhoneType::getPhoneTypeId,Phone::getPhoneTypeId)
                .eq(Phone::getPhoneId,id);
        List<PhoneAndTypeAndDetailetAndImgDTO> phoneAndTypeDTOS = phoneMapper.selectJoinList(PhoneAndTypeAndDetailetAndImgDTO.class, mpjLambdaWrapper);
        return  ResponseModel.success(ResCode.SUCCESS,phoneAndTypeDTOS);
    }

    //后台使用
    @Override
    public ResponseModel findPhoneListPageMsg(Map<String, Object> map) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<Phone>()
                .selectAll(Phone.class)
                .select(PhoneDetailet::getColor,PhoneDetailet::getPrice,PhoneDetailet::getRam,PhoneDetailet::getStorage,PhoneDetailet::getVersion,PhoneDetailet::getScreenSize,PhoneDetailet::getQuantity)
                .select(PhoneType::getPhoneType)
                .innerJoin(PhoneDetailet.class,PhoneDetailet::getPhoneId,Phone::getPhoneId)
                .innerJoin(PhoneType.class,PhoneType::getPhoneTypeId,Phone::getPhoneTypeId)
                .eq(map.get("phoneDetailetId")!=null,PhoneDetailet::getPhoneDetailetId,map.get("phoneDetailetId"))
                .eq(map.get("phoneType")!=null,PhoneType::getPhoneType,map.get("phoneType"));

        Page phonePage=new Page<>((Integer)map.get("pageNumber"),(Integer)map.get("pageSize"));

        IPage<PhoneAndPhoneDetailetAndphoneTypeDTO> pageDTOS = phoneMapper.selectJoinPage(phonePage, PhoneAndPhoneDetailetAndphoneTypeDTO.class, mpjLambdaWrapper);
        return  ResponseModel.success(ResCode.SUCCESS,pageDTOS);
    }

}
