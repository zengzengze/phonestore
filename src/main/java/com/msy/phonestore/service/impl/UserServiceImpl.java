package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msy.phonestore.mapper.UserMapper;
import com.msy.phonestore.pojo.Users;
import com.msy.phonestore.service.ifc.IUserService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/24/13:59
 * @Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseModel Login(Users users) throws Exception {
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userId",users.getUserId()).eq("password",users.getPassword());
        Users users1 = userMapper.selectOne(queryWrapper);

        if(users1!=null){
            return ResponseModel.success(ResCode.SUCCESS,users1);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }
}
