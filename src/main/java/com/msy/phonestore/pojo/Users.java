package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "users")
public class Users {
    @TableId(value = "userId")
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
    @TableField(value = "uAddress")
    private String uAddress;
    private Integer grade;
    private String email;
    @TableField(value = "pNumber")
    private String pNumber;
}
