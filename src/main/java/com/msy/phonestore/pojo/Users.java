package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName(value = "users")
@KeySequence(value = "USERS_USERID_SEQ")
public class Users {
    @TableId(value = "userId",type = IdType.INPUT)
    private Integer userId;
    @TableField(value = "userName")
    private String userName;
    private String name;
    private String password;
    private Integer gender;
    private String birthday;
    @TableField(value = "userImg")
    private String userImg;
    private String province;
    private String city;
    private Integer grade;
    private String email;
    @TableField(value = "pNumber")
    private String pNumber;
}
