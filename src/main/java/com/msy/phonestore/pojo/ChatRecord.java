package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/09/17:38
 * @Description:
 */
@Data
@TableName(value = "Chat_Record")
@KeySequence(value = "seq_chat_record_recordId")
public class ChatRecord {
    @TableId(value = "RecordId",type = IdType.INPUT)
    private Integer recordId;
    @TableField(value = "adminId")
    private Integer adminId;
    @TableField(value = "userId")
    private Integer userId;
    @TableField(value = "unReadCount")
    private Integer unReadCount;
    @TableField(value = "chatRecord")
    private String chatRecord;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "dateTime")
    private Date dateTime;

    @TableField(exist = false)
    private Users user;

    @TableField(exist = false)
    private List<Message> messageList;
}
