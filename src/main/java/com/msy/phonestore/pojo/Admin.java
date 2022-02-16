package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/09/16:48
 * @Description:
 */
@Data
public class Admin {
    @TableId(value = "adminId")
    private Integer adminId;
    @TableField(value = "adminName")
    private String adminName;
    private String password;
}
