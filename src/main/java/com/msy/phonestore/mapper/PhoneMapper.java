package com.msy.phonestore.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.msy.phonestore.pojo.Phone;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/24/15:49
 * @Description:
 */
public interface PhoneMapper extends MPJBaseMapper<Phone> {

    public List<Phone> queryMsgByMap(Map<String,Object> map) throws Exception;

    public int queryMsgByMapCount(Map<String,Object> map) throws Exception;

    public Phone queryByPhoneId(Integer phoneId) throws Exception;

//    public Phone queryBuyNowByMap(Map<String,Object> map)throws Exception;
}
