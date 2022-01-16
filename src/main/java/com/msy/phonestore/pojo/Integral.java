package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/12/20:04
 * @Description:
 */
@Data
public class Integral {

    @TableId(value = "integralId")
    private Integer integralId;

    @TableField(value = "integralCount")
    private Integer integralCount;

    @TableField(value = "userId")
    private Integer userId;
}
