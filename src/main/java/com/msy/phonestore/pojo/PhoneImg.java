package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/20/17:43
 * @Description:
 */
@Data
@TableName(value = "phone_Img")
public class PhoneImg {

    @TableId(value = "phoneImgId")
    private Integer phoneImgId;
    @TableField(value = "phoneDetailetId")
    private Integer phoneDetailetId;
    @TableField(value = "imgOne")
    private String imgOne;
    @TableField(value = "imgTwo")
    private String imgTwo;
    @TableField(value = "imgThree")
    private String imgThree;
    @TableField(value = "imgFour")
    private String imgFour;
    @TableField(value = "imgFive")
    private String imgFive;
}
