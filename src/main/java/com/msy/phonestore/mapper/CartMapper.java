package com.msy.phonestore.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.msy.phonestore.pojo.Cart;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/25/14:29
 * @Description:
 */
public interface CartMapper extends MPJBaseMapper<Cart> {

    public List<Cart> queryMsgByMap(Integer userId)throws Exception;
}
