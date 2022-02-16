package com.msy.phonestore.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/01/16:49
 * @Description:
 */
@Data
public class Message {

    private Integer id;
    private String channel;
    private Boolean messageType;
    private Boolean isSystem;
    private Object message;
    private Integer receiver;
    private Integer sender;
    private Date time;
    private String timestamp;
    private String type;
    private String userId;
    private String userNickName;

}
