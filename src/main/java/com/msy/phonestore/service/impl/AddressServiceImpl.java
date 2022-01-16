package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.AddressAndProvinceAndCityAndCountyDTO;
import com.msy.phonestore.mapper.AddressMapper;
import com.msy.phonestore.pojo.*;
import com.msy.phonestore.service.ifc.IAddressService;
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
 * @Date: 2021/12/30/15:06
 * @Description:
 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public ResponseModel findMsgByMap(Map<String,Object> map) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
                .selectAll(Address.class)
                .select(Province::getProvince)
                .select(City::getCity)
                .select(County::getCounty)
                .innerJoin(Province.class,Province::getProvinceId,Address::getProvinceId)
                .innerJoin(City.class,City::getCityId,Address::getCityId)
                .innerJoin(County.class,County::getCountyId,Address::getCountyId)
                .eq(map.get("addressId")!=null,Address::getAddressId,map.get("addressId"))
                .eq(map.get("userId")!=null,Address::getUserId,map.get("userId"))
                .eq(map.get("defaultState")!=null,Address::getDefaultState,map.get("defaultState"))
                .eq(map.get("state")!=null,Address::getState,map.get("state"))
                .orderByDesc(Address::getState);
        List<AddressAndProvinceAndCityAndCountyDTO> DTOS = addressMapper.selectJoinList(AddressAndProvinceAndCityAndCountyDTO.class, mpjLambdaWrapper);
        if(DTOS.isEmpty()){
            List<AddressAndProvinceAndCityAndCountyDTO> DTOS1 = addressMapper.findFirstAddress((Integer) map.get("userId"));
            return ResponseModel.success(ResCode.SUCCESS, DTOS1);
        }
        return ResponseModel.success(ResCode.SUCCESS,DTOS);
    }

    @Override
    public ResponseModel findMsgById(Integer id) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper=new MPJLambdaWrapper<>()
                .selectAll(Address.class)
                .select(Province::getProvince)
                .select(City::getCity)
                .select(County::getCounty)
                .innerJoin(Province.class,Province::getProvinceId,Address::getProvinceId)
                .innerJoin(City.class,City::getCityId,Address::getCityId)
                .innerJoin(County.class,County::getCountyId,Address::getCountyId)
                .eq(id!=null,Address::getAddressId,id);
        AddressAndProvinceAndCityAndCountyDTO DTO=addressMapper.selectJoinOne(AddressAndProvinceAndCityAndCountyDTO.class,mpjLambdaWrapper);
        return ResponseModel.success(ResCode.SUCCESS,DTO);
    }

    @Override
    @Transactional
    public ResponseModel updateAddressMsg(Address address) throws Exception {
        Address address1 = addressMapper.selectOne(new QueryWrapper<Address>().eq("userId", address.getUserId()).eq("state", 1));
        if(address1!=null){
            Address address2=new Address();
            address2.setAddressId(address1.getAddressId());
            address2.setState(0);
            int row1 = addressMapper.updateById(address2);
            if(row1==0){
                return ResponseModel.fail(ResCode.FAIL);
            }
        }
        int row = addressMapper.updateById(address);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Transactional
    @Override
    public ResponseModel insertAddressMsg(Address address) throws Exception {
            address.setState(0);
            int row = addressMapper.insert(address);
            if(row>0){
                return ResponseModel.success(ResCode.SUCCESS);
            }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel updateByAddressMsg(Address address) throws Exception {
        int row = addressMapper.updateById(address);
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseModel setDefault(Address address) throws Exception {
        Address address1 = addressMapper.selectOne(new QueryWrapper<Address>().eq("defaultState", 1).eq("userId",address.getUserId()));
        if(address1!=null){
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
        }else {
            int row = addressMapper.updateById(address);
            if(row>0){
                return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel delAddressMsgByIds(Integer[] ids) throws Exception {
        int row=0;
        for(int i=0;i<ids.length;i++){
            row += addressMapper.deleteById(ids[i]);
        }
        if(row>0){
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel updateAddressState(Integer userId) throws Exception {

        List<Address> addresses = addressMapper.selectList(new QueryWrapper<Address>().eq("userId", userId).eq("state", 1));
        int row=0;
        if(!addresses.isEmpty()){
            for(Address address:addresses){
                Address address1=new Address();
                address1.setAddressId(address.getAddressId());
                address1.setState(0);
                int row1 = addressMapper.updateById(address1);
                if(row1>0){
                    row++;
                }
            }
            if(row>0){
                return ResponseModel.success(ResCode.SUCCESS);
            }

        }else {
            return ResponseModel.success(ResCode.SUCCESS);
        }

        return ResponseModel.success(ResCode.FAIL);
    }
}
