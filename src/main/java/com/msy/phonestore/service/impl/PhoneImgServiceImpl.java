package com.msy.phonestore.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msy.phonestore.mapper.PhoneImgMapper;
import com.msy.phonestore.pojo.PhoneImg;
import com.msy.phonestore.service.ifc.IPhoneImgService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/20/17:42
 * @Description:
 */
@Service
public  class PhoneImgServiceImpl implements IPhoneImgService {

    @Autowired
    private PhoneImgMapper phoneImgMapper;

    @Override
    public ResponseModel findPhoneImgMsg(Map<String, Object> map) throws Exception {
        PhoneImg phoneImg = phoneImgMapper.selectOne(new QueryWrapper<PhoneImg>()
                .eq(map.get("phoneDetailId") != null, "phoneDetailId", map.get("phoneDetailId")));
//        List list=new ArrayList();

        String[] imgList=new String[5];
        imgList[0]=phoneImg.getImgOne();
        imgList[1]=phoneImg.getImgTwo();
        imgList[2]=phoneImg.getImgThree();
        imgList[3]=phoneImg.getImgFour();
        imgList[4]=phoneImg.getImgFive();

        phoneImg.setImgList(imgList);
        return ResponseModel.success(ResCode.SUCCESS,phoneImg);
    }

    @Override
    public ResponseModel updatePhoneImgByMapMsg(Map<String,Object> map) throws Exception {

        PhoneImg phoneImg=JSON.parseObject(JSON.toJSONString(map.get("phoneImg")),PhoneImg.class);

        String[] imgList= phoneImg.getImgList();

        phoneImg.setImgOne(imgList[0]);
        phoneImg.setImgTwo(imgList[1]);
        phoneImg.setImgThree(imgList[2]);
        phoneImg.setImgFour(imgList[3]);
        phoneImg.setImgFive(imgList[4]);

        int row = phoneImgMapper.updateById(phoneImg);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}
