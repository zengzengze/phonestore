package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msy.phonestore.mapper.PhoneMapper;
import com.msy.phonestore.pojo.Phone;
import com.msy.phonestore.service.ifc.IPhoneService;
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

    @Override
    public ResponseModel findMsgByMap(Map<String, Object> map) throws Exception {

        QueryWrapper<Phone> phoneQueryWrapper=new QueryWrapper<>();

        phoneQueryWrapper.like("pname",map.get("pname"));

        Page<Phone> phonePage=new Page<>((Integer)map.get("pageNumber"),(Integer)map.get("pageSize"));

        IPage<Map<String, Object>> phoneIPage = phoneMapper.selectMapsPage(phonePage, phoneQueryWrapper);

        System.out.println("总页数： "+phoneIPage.getPages());
        System.out.println("总记录数： "+phoneIPage.getTotal());
        phoneIPage.getRecords().forEach(System.out::println);
        return ResponseModel.success(ResCode.SUCCESS,phoneIPage);
    }
}
