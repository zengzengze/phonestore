package com.msy.phonestore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/13/21:27
 * @Description:
 */
@Data
public class DeliveryToolsInfo {

    private String expName;
    private String takeTime;
    private String expSite;
    private String expPhone;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    private String number;
    private String type;
    private Integer deliverystatus;
    private Integer issign;
    private String courier;
    private String courierPhone;
    private String logo;

    private List<DeliveryTools> list;
}
