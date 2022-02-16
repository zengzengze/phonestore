package com.msy.phonestore.mapper;


import com.github.yulichang.base.MPJBaseMapper;
import com.msy.phonestore.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/25/14:27
 * @Description:
 */
public interface OrderMapper extends MPJBaseMapper<Orders> {

    public List<Orders> queryPageMsgByMap(Map<String,Object> map)throws Exception;

    public List<Orders> queryMsgByMap(Map<String,Object> map)throws Exception;

    public Orders queryOneMsgByMap(Map<String,Object> map)throws Exception;

    public int queryCountByMap(Map<String,Object> map)throws Exception;
}
