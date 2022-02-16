package com.msy.phonestore.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.msy.phonestore.pojo.ChatRecord;
import org.apache.ibatis.annotations.Update;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/09/17:41
 * @Description:
 */
public interface ChatRecordMapper extends MPJBaseMapper<ChatRecord> {

    @Update("update chat_record set unReadCount=unReadCount+1,dateTime=sysdate where recordId=#{recordId}")
    public int updateUnReadCount(Integer recordId)throws Exception;
    //变更客服
    @Update("update chat_record set adminId=#{adminId} where userId=#{userId}")
    public int updateChatRecordByUserIdMsg(Integer userId,Integer adminId)throws Exception;

    @Update("update chat_record set unReadCount=0 where userId=#{userId}")
    public int cancelUnReadCountByUserIdMsg(Integer userId)throws Exception;
}
