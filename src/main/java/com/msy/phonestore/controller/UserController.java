package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.Users;
import com.msy.phonestore.service.ifc.IUserService;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/24/13:59
 * @Description:
 */
@RestController
    @RequestMapping("/user")
    public class UserController {

        @Autowired
        private IUserService userService;

        @RequestMapping("/login")
        public ResponseModel Login(@RequestBody Users users)throws Exception{
            ResponseModel model = userService.Login(users);
            return model;
        }

    @RequestMapping("/findUserListMsg")
    public ResponseModel findUserListMsg(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = userService.findUserListMsg(map);
        return model;
    }
}
