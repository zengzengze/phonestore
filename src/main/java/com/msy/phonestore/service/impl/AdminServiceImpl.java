package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msy.phonestore.mapper.AdminMapper;
import com.msy.phonestore.pojo.Admin;
import com.msy.phonestore.service.ifc.IAdminService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/09/16:52
 * @Description:
 */
@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ResponseModel backLogin(Map<String, Object> map) throws Exception {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("adminId", map.get("adminId"))
                .eq("password", map.get("password")));
        if(admin!=null){
            return ResponseModel.success(ResCode.SUCCESS,admin);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}
