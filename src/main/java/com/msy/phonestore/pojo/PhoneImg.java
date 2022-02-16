package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
@KeySequence(value = "SEQ_PHONEIMG_PHONEIMGID")
public class PhoneImg {

    @TableId(value = "phoneImgId",type = IdType.INPUT)
    private Integer phoneImgId;
    @TableField(value = "phoneDetailId")
    private Integer phoneDetailId;
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

    //保存图片列表
    @TableField(exist = false)
    private String[] imgList;
}
