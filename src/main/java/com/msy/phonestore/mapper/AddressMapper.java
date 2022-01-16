package com.msy.phonestore.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.msy.phonestore.dto.AddressAndProvinceAndCityAndCountyDTO;
import com.msy.phonestore.pojo.Address;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/25/14:30
 * @Description:
 */
public interface AddressMapper extends MPJBaseMapper<Address> {

    @Select("select max(state) from address where userId=#{userId}")
    public int findAddressById(Address address)throws Exception;

    @Select("select * from address a,province p,city c,county c1 where a.provinceId=p.provinceId and a.cityId=c.cityId and a.countyId=c1.countyId and userId=#{userId} and rowNum<=1")
    public List<AddressAndProvinceAndCityAndCountyDTO> findFirstAddress(Integer userId)throws Exception;

}
