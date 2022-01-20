package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msy.phonestore.mapper.PhoneImgMapper;
import com.msy.phonestore.pojo.PhoneImg;
import com.msy.phonestore.service.ifc.IPhoneImgService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/20/17:42
 * @Description:
 */
@Service
public class PhoneImgServiceImpl implements IPhoneImgService {

    @Autowired
    private PhoneImgMapper phoneImgMapper;

    @Override
    public ResponseModel findPhoneImgMsg(Map<String, Object> map) throws Exception {
        PhoneImg phoneImg = phoneImgMapper.selectOne(new QueryWrapper<PhoneImg>()
                .eq(map.get("phoneDetailetId") != null, "phoneDetailetId", map.get("phoneDetailetId")));
        List list=new ArrayList();
        list.add(0,phoneImg.getImgOne());
        list.add(1,phoneImg.getImgTwo());
        list.add(2,phoneImg.getImgThree());
        list.add(3,phoneImg.getImgFour());
        list.add(4,phoneImg.getImgFive());
        return ResponseModel.success(ResCode.SUCCESS,list);
    }
}
