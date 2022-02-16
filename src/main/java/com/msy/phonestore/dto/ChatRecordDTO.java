package com.msy.phonestore.dto;

import com.msy.phonestore.pojo.FileData;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/08/10:57
 * @Description:
 */
@Data
public class ChatRecordDTO {
    private String textName;
    private List<FileData> fileDataList;
}
