package com.msy.phonestore.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.msy.phonestore.pojo.Integral;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/12/20:06
 * @Description:
 */
public interface IntegralMapper extends MPJBaseMapper<Integral> {

    @Update("update integral set integralCount=integralCount-(#{pointsOffer}*100) where userId=#{userId}")
    public int updateIntegralMsg(Map<String,Object> map)throws Exception;

    //确认收货获得积分
    @Update("update integral set integralCount=integralCount+#{integralCount} where userId=#{userId}")
    public int updateIntegral(Integral integral)throws Exception;
}
