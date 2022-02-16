package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msy.phonestore.mapper.UserMapper;
import com.msy.phonestore.pojo.FileData;
import com.msy.phonestore.pojo.Message;
import com.msy.phonestore.pojo.Users;
import com.msy.phonestore.service.ifc.IUserService;
import com.msy.phonestore.vo.CreateCode;
import com.msy.phonestore.vo.FileStreamUtils;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.msy.phonestore.vo.CreateCode.*;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

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
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", users.getUserId()).eq("password", users.getPassword());
        users = userMapper.selectOne(queryWrapper);
        if (users != null) {
            return ResponseModel.success(ResCode.SUCCESS, users);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    @Transactional
    public ResponseModel loginByPhoneNumber(String phoneNumber) throws Exception {
        System.out.println(phoneNumber);

        Users user = userMapper.selectOne(new QueryWrapper<Users>()
                .eq("phoneNumber", phoneNumber));
        if (user != null) {
            return ResponseModel.success(ResCode.SUCCESS, user);
        } else {
            Users users = new Users();
            users.setPhoneNumber(phoneNumber);
            users.setUserName(getCreateUserName());
            int row = userMapper.insert(users);
            if (row > 1) {
                Users user1 = userMapper.selectOne(new QueryWrapper<Users>()
                        .eq("userName", user.getUserName())
                        .eq("phoneNumber", user.getPhoneNumber()));

                return ResponseModel.success(ResCode.SUCCESS, user1);
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel findUserListMsg(Map<String, Object> map) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper<Users>()
                .eq(map.get("name") != null, "name", map.get("name"))
                .eq(map.get("phoneNumber") != null, "phoneNumber", map.get("phoneNumber"));

        Page userPage = new Page<>((Integer) map.get("pageNumber"), (Integer) map.get("pageSize"));
        Page page = userMapper.selectPage(userPage, queryWrapper);
        return ResponseModel.success(ResCode.SUCCESS, page);
    }

    @Override
    public ResponseModel findUserIdMsg(Integer userId) throws Exception {
        Users users = userMapper.selectById(userId);
        List<Message> messageList = FileStreamUtils.reader(String.valueOf(userId));
        users.setMessageList(messageList);
        return ResponseModel.success(ResCode.SUCCESS, users);
    }
}
