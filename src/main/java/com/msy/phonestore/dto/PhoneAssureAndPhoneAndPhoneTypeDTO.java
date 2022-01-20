package com.msy.phonestore.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/20/11:43
 * @Description:
 */
@Data
public class PhoneAssureAndPhoneAndPhoneTypeDTO {
    private Integer assureId;
    private String assure;
    private double assurePrice;
    private Integer phoneId;
    private String assureImg;

    private String phoneName;
    private Integer phoneTypeId;
    private String phoneImg;
    private Integer remark;
    private double praise;

    private String PhoneType;

}
