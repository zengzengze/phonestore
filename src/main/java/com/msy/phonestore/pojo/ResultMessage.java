package com.msy.phonestore.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/01/16:50
 * @Description:
 */
@Data
public class ResultMessage {
    private boolean isSystem;
    private Integer fromUserId;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date dateTime;
    private Object message;
    private String orderId;
}
