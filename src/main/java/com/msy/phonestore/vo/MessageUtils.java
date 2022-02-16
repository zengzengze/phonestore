package com.msy.phonestore.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msy.phonestore.pojo.Message;
import com.msy.phonestore.pojo.ResultMessage;

import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/01/16:53
 * @Description:
 */
public class MessageUtils {

    public static String getMessage(Message message) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(message);
    }
}
