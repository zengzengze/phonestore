package com.msy.phonestore.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/05/17:05
 * @Description:
 */
@Data
public class ProvinceAndCityAndCountyDTO {
    private Integer provinceId;
    private String province;
    private Integer cityId;
    private String city;
    private Integer countyId;
    private String county;
}
