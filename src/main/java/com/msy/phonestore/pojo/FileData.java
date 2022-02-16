package com.msy.phonestore.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/07/17:29
 * @Description:
 */
@Data
public class FileData {

    private Date dateTime;
    private Integer userId;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private String textData;
    private boolean orderPanel;

}
