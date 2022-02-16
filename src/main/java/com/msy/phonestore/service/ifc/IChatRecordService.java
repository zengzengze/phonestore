package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.ChatRecord;
import com.msy.phonestore.vo.ResponseModel;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/09/17:41
 * @Description:
 */
public interface IChatRecordService {

    ResponseModel findByAdminIdMsg(Integer adminId)throws Exception;

    ResponseModel updateByChatRecordMsg(ChatRecord ChatRecord)throws Exception;

    ResponseModel insertByChatRecordMsg(ChatRecord ChatRecord)throws Exception;

    ResponseModel updateByUserIdAndAdminIdMsg(Integer userId,Integer adminId)throws Exception;

    ResponseModel cancelUnReadCountByUserIdMsg(Integer userId)throws Exception;

}
