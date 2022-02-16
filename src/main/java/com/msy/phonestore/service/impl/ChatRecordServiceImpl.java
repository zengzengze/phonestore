package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msy.phonestore.mapper.ChatRecordMapper;
import com.msy.phonestore.mapper.UserMapper;
import com.msy.phonestore.pojo.ChatRecord;
import com.msy.phonestore.pojo.FileData;
import com.msy.phonestore.pojo.Message;
import com.msy.phonestore.pojo.Users;
import com.msy.phonestore.service.ifc.IChatRecordService;
import com.msy.phonestore.vo.FileStreamUtils;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/09/17:42
 * @Description:
 */
@Service
public class ChatRecordServiceImpl implements IChatRecordService {

    @Autowired
    private ChatRecordMapper chatRecordMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseModel findByAdminIdMsg(Integer adminId) throws Exception {
        List<ChatRecord> chatRecordList = chatRecordMapper.selectList(new QueryWrapper<ChatRecord>()
                .eq("adminId", adminId)
                .orderByDesc("dateTime"));
        for (ChatRecord chatRecord : chatRecordList) {
            Users user = userMapper.selectById(chatRecord.getUserId());
            List<Message> messageList = FileStreamUtils.reader(String.valueOf(chatRecord.getUserId()));
            chatRecord.setUser(user);
            chatRecord.setMessageList(messageList);
        }
        return ResponseModel.success(ResCode.SUCCESS, chatRecordList);
    }

    @Override
    public ResponseModel updateByChatRecordMsg(ChatRecord chatRecord) {
        int row = chatRecordMapper.updateById(chatRecord);
        if (row > 0) {
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseModel insertByChatRecordMsg(ChatRecord chatRecord) throws Exception {
        ChatRecord ser = chatRecordMapper.selectOne(new QueryWrapper<ChatRecord>()
                .eq("userId", chatRecord.getUserId()));
        if (ser != null) {
            int row = chatRecordMapper.updateUnReadCount(ser.getRecordId());
            if (row > 0) {
                return ResponseModel.success(ResCode.SUCCESS);
            }
        } else {
            int row = chatRecordMapper.insert(chatRecord);
            if (row > 0) {
                return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel updateByUserIdAndAdminIdMsg(Integer userId, Integer adminId) throws Exception {
        int row = chatRecordMapper.updateChatRecordByUserIdMsg(userId,adminId);
        if (row > 0) {
            return ResponseModel.success(ResCode.SUCCESS,"服务转接成功");
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel cancelUnReadCountByUserIdMsg(Integer userId) throws Exception {
        int row = chatRecordMapper.cancelUnReadCountByUserIdMsg(userId);
        if (row > 0) {
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}
