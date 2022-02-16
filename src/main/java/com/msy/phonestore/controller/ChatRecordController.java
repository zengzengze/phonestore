package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.ChatRecord;
import com.msy.phonestore.service.ifc.IChatRecordService;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/09/16:54
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/serviceRecord")
public class ChatRecordController {
    @Autowired
    private IChatRecordService serviceRecordService;

    @RequestMapping("/findByAdminIdMsg")
    public ResponseModel findByAdminIdMsg(Integer adminId)throws Exception{
        ResponseModel model = serviceRecordService.findByAdminIdMsg(adminId);
        return model;
    }

    @RequestMapping("/updateByChatRecordMsg")
    public ResponseModel updateByChatRecordMsg(@RequestBody ChatRecord serviceRecord)throws Exception{
        ResponseModel model = serviceRecordService.updateByChatRecordMsg(serviceRecord);
        return model;
    }

    @RequestMapping("/insertByChatRecordMsg")
    public ResponseModel insertByChatRecordMsg(@RequestBody ChatRecord serviceRecord)throws Exception{
        System.out.println(serviceRecord);
        ResponseModel model = serviceRecordService.insertByChatRecordMsg(serviceRecord);
        return model;
    }


    @RequestMapping("/cancelUnReadCountByUserIdMsg")
    public ResponseModel cancelUnReadCountByUserIdMsg(Integer userId)throws Exception{
        ResponseModel model = serviceRecordService.cancelUnReadCountByUserIdMsg(userId);
        return model;
    }
}
