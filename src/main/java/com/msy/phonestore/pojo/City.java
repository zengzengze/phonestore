package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/05/17:01
 * @Description:
 */
@Data
public class City {
    @TableId(value = "cityId")
    private Integer cityId;
    private String city;
    private Integer provinceId;
}
