package com.msy.phonestore.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.PhoneAndTypeAndDetailAndImgDTO;
import com.msy.phonestore.mapper.*;
import com.msy.phonestore.pojo.*;
import com.msy.phonestore.service.ifc.IPhoneService;
import com.msy.phonestore.vo.PageUtil;
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
 * @Date: 2021/11/24/15:50
 * @Description:
 */
@Service
public class PhoneServiceImpl implements IPhoneService {

    @Autowired
    private PhoneMapper phoneMapper;
    @Autowired
    private PhoneDetailMapper phoneDetailMapper;
    @Autowired
    private PhoneAssureMapper phoneAssureMapper;
    @Autowired
    private PhoneImgMapper phoneImgMapper;
    @Autowired
    private PhoneComboMapper phoneComboMapper;
    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public ResponseModel findMsgByMap(Map<String, Object> map) throws Exception {
//        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
//                .selectAll(Phone.class)
//                .select(PhoneType::getPType)
//                .select(PhoneDetail::getPDetailId,PhoneDetail::getRam,PhoneDetail::getStorage,PhoneDetail::getVersion,
//                        PhoneDetail::getColor,PhoneDetail::getPrice,PhoneDetail::getScreenSize,
//                        PhoneDetail::getQuantity)
//                .innerJoin(PhoneDetail.class,PhoneDetail::getPhoneId,Phone::getPhoneId)
//                .innerJoin(PhoneType.class, PhoneType::getPTypeId,Phone::getPTypeId)
//                .eq(map.get("pTypeId")!=null,PhoneType::getPTypeId,map.get("pTypeId"));
        MPJQueryWrapper queryWrapper = new MPJQueryWrapper<>()
                .select("t.*")
                .select("pt.phoneType")
                .select("pd.phoneDetailId,pd.Ram,pd.Storage,pd.Version,pd.Color,pd.Price,pd.ScreenSize")
                .innerJoin("Phone_Type pt on t.phoneTypeId=pt.phoneTypeId")
                .innerJoin("Phone_Detail pd on t.PhoneId = pd.PhoneId")
                .eq(map.get("phoneTypeId") != null, "t.phoneTypeId", map.get("phoneTypeId"));
        Page phonePage = new Page<>((Integer) map.get("pageNumber"), (Integer) map.get("pageSize"));
        IPage<PhoneAndTypeAndDetailAndImgDTO> phoneIPage = phoneMapper.selectJoinPage(phonePage, PhoneAndTypeAndDetailAndImgDTO.class, queryWrapper);

//        System.out.println("总页数： "+phoneIPage.getPages());
//        System.out.println("总记录数： "+phoneIPage.getTotal());
//        phoneIPage.getRecords().forEach(System.out::println);
        return ResponseModel.success(ResCode.SUCCESS, phoneIPage);
    }

    @Override
    public ResponseModel findMsgById(Integer id) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper = new MPJLambdaWrapper<>()
                .select(Phone::getPhoneId, Phone::getPhoneName, Phone::getPhoneImg, Phone::getRemark, Phone::getPraise)
                .select(PhoneType::getPhoneType)
                .select(PhoneDetail::getPhoneDetailId, PhoneDetail::getRam, PhoneDetail::getStorage, PhoneDetail::getVersion,
                        PhoneDetail::getColor, PhoneDetail::getPrice, PhoneDetail::getScreenSize,
                        PhoneDetail::getQuantity)
                .innerJoin(PhoneDetail.class, PhoneDetail::getPhoneId, Phone::getPhoneId)
                .innerJoin(PhoneType.class, PhoneType::getPhoneTypeId, Phone::getPhoneTypeId)
                .eq(Phone::getPhoneId, id);
        List<PhoneAndTypeAndDetailAndImgDTO> phoneAndTypeDTOS = phoneMapper.selectJoinList(PhoneAndTypeAndDetailAndImgDTO.class, mpjLambdaWrapper);
        return ResponseModel.success(ResCode.SUCCESS, phoneAndTypeDTOS);
    }

    //后台使用
    @Override
    public ResponseModel findPhoneListPageMsg(Map<String, Object> map) throws Exception {
//        MPJLambdaWrapper mpjLambdaWrapper = new MPJLambdaWrapper<Phone>()
//                .selectAll(Phone.class)
//                .select(PhoneDetail::getColor, PhoneDetail::getPrice, PhoneDetail::getRam, PhoneDetail::getStorage, PhoneDetail::getVersion, PhoneDetail::getScreenSize, PhoneDetail::getQuantity)
//                .select(PhoneType::getPhoneType)
//                .innerJoin(PhoneDetail.class, PhoneDetail::getPhoneId, Phone::getPhoneId)
//                .innerJoin(PhoneType.class, PhoneType::getPhoneTypeId, Phone::getPhoneTypeId)
//                .eq(map.get("phoneTypeId") != null && map.get("phoneTypeId") != "", Phone::getPhoneTypeId, map.get("phoneTypeId"))
//                .like(map.get("phoneName") != null && map.get("phoneName") != "", Phone::getPhoneName, map.get("phoneName"));
//
//        Page phonePage = new Page<>((Integer) map.get("pageNumber"), (Integer) map.get("pageSize"));
//
//        IPage<PhoneAndPhoneDetailAndphoneTypeDTO> pageDTOS = phoneMapper.selectJoinPage(phonePage, PhoneAndPhoneDetailAndphoneTypeDTO.class, mpjLambdaWrapper);
//        return ResponseModel.success(ResCode.SUCCESS, pageDTOS);

        List<Phone> phoneList = phoneMapper.queryMsgByMap(map);
        PageUtil<Phone> pageUtil = new PageUtil<>();
        pageUtil.setPageNumber((Integer) map.get("pageNumber"));
        pageUtil.setPageSize((Integer) map.get("pageSize"));
        pageUtil.setList(phoneList);

        pageUtil.setTotal(phoneMapper.queryMsgByMapCount(map));
        pageUtil.setPageCount();

        return ResponseModel.success(ResCode.SUCCESS, pageUtil);
    }

    //添加手机
    @Override
    @Transactional
    public ResponseModel insertPhoneMsg(Map<String, Object> map) throws Exception {

        Phone phone = JSON.parseObject(JSON.toJSONString(map.get("phone")), Phone.class);

        Phone selectOne = phoneMapper.selectOne(new QueryWrapper<Phone>()
                .eq("phoneName", phone.getPhoneName()));
        if (selectOne != null) {
            return ResponseModel.fail(ResCode.FAIL, "不可以重复添加!");
        } else {
            int row = phoneMapper.insert(phone);
            int row1 = 0;
            int row2 = 0;
            int row3 = 0;
            int row4=0;
            if (row > 0) {
                Phone phone1 = phoneMapper.selectOne(new QueryWrapper<Phone>()
                        .eq("phoneName", phone.getPhoneName())
                        .eq("phoneTypeId", phone.getPhoneTypeId()));

                for (PhoneDetail pd : phone.getPhoneDetailList()) {
                    pd.setPhoneId(phone1.getPhoneId());
                    row1 += phoneDetailMapper.insert(pd);

                    PhoneDetail phoneDetail = phoneDetailMapper.selectOne(new QueryWrapper<PhoneDetail>()
                            .eq("color", pd.getColor())
                            .eq("phoneId", pd.getPhoneId())
                            .eq("ram", pd.getRam())
                            .eq("storage", pd.getStorage())
                            .eq("version", pd.getVersion())
                            .eq("screenSize", pd.getScreenSize()));

                    PhoneImg phoneImg = new PhoneImg();
                    phoneImg.setPhoneDetailId(phoneDetail.getPhoneDetailId());

                    phoneImg.setImgOne(pd.getImgList()[0]);
                    phoneImg.setImgTwo(pd.getImgList()[1]);
                    phoneImg.setImgThree(pd.getImgList()[2]);
                    phoneImg.setImgFour(pd.getImgList()[3]);
                    phoneImg.setImgFive(pd.getImgList()[4]);

                    row3 += phoneImgMapper.insert(phoneImg);
                }

                for (PhoneAssure pa : phone.getPhoneAssureList()) {
                    pa.setPhoneId(phone1.getPhoneId());
                    row2 += phoneAssureMapper.insert(pa);
                }
                for (PhoneCombo pc : phone.getPhoneComboList()) {
                    pc.setPhoneId(phone1.getPhoneId());
                    row4 += phoneComboMapper.insert(pc);
                }
            }
            if (row1 > 0 && row2 > 0 && row3 > 0 && row4>0) {
                return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel findByPhoneIdMsg(Integer phoneId) throws Exception {
//        Phone phone = phoneMapper.selectById(phoneId);
        Phone phone = phoneMapper.queryByPhoneId(phoneId);
        for(PhoneCombo phoneCombo:phone.getPhoneComboList()){
            if(phoneCombo.getCommodityId()!=null){
                Commodity commodity = commodityMapper.selectById(phoneCombo.getCommodityId());
                phoneCombo.setCommodity(commodity);
            }
        }
        return ResponseModel.success(ResCode.SUCCESS, phone);
    }

}
