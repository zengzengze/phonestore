package com.msy.phonestore.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/05/13:57
 * @Description:
 */
@Data
public class AddressAndProvinceAndCityAndCountyDTO {

    @TableId(value = "addressId")
    private Integer addressId;
    @TableField(value = "userId")
    private Integer userId;
    private Integer provinceId;
    private Integer cityId;
    private Integer countyId;
    private String address;
    private String name;
    private String phoneNumber;
    @TableField(value = "defaultState")
    private Integer defaultState;
    private Integer state;
    private String fixedNumber;

    private String province;

    private String city;

    private String county;
}
