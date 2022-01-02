package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.mapper.AddressMapper;
import com.msy.phonestore.pojo.Address;
import com.msy.phonestore.service.ifc.IAddressService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/12/30/15:06
 * @Description:
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Override
    public ResponseModel findMsgByMap(Map<String,Object> map) throws Exception {
        List<Address> addressList = addressMapper.selectList(new QueryWrapper<Address>()
        .eq(map.get("addressId")!=null,"addressId",map.get("addressId"))
        .eq(map.get("userId")!=null,"userId",map.get("userId"))
                .eq(map.get("state")!=null,"state",map.get("state"))
        .orderByAsc("state"));
        return ResponseModel.success(ResCode.SUCCESS,addressList);
    }

    @Override
    @Transactional
    public ResponseModel updateAddressMsg(Address address) throws Exception {
        Address address1 = addressMapper.selectOne(new QueryWrapper<Address>().eq("state", 1));
        Address address2 = addressMapper.selectOne(new QueryWrapper<Address>().eq("addressId", address.getAddressId()));
        if(address1.getState()==address.getState() && address1.getAddressId()==address.getAddressId()){
            return ResponseModel.success(ResCode.SUCCESS);
        }else {
            Address address3=new Address();
            address3.setAddressId(address1.getAddressId());
            address3.setState(address2.getState());

            int row = addressMapper.updateById(address);
            int row1 = addressMapper.updateById(address3);

            if(row>0 && row1>0){
                return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseModel setDefault(Address address) throws Exception {
        Address address1 = addressMapper.selectOne(new QueryWrapper<Address>().eq("defaultState", 1));
        System.out.println(address1.getAddressId());
        if(address1.getAddressId()==address.getAddressId() && address.getState()==address1.getState()){
            return ResponseModel.success(ResCode.SUCCESS,"不需要修改!");
        }else {
            int row = addressMapper.updateById(address);
            Address address2=new Address();
            address2.setAddressId(address1.getAddressId());
            address2.setDefaultState(0);
            int row1 = addressMapper.updateById(address2);
            if(row>0 && row1>0){
                return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel delAddressMsgByIds(Integer[] ids) throws Exception {
        int row=0;
        for(int i=0;i<ids.length;i++){
            System.out.println(ids[i]);
            row += addressMapper.deleteById(ids[i]);
        }
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}
