package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.Users;
import com.msy.phonestore.vo.ResponseModel;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/24/13:59
 * @Description:
 */

public interface IUserService {

    //密码登录
    ResponseModel Login(Users users)throws Exception;
    //验证码登录
    ResponseModel loginByPhoneNumber(String phoneNumber)throws Exception;

    ResponseModel findUserListMsg(Map<String,Object> map)throws Exception;

    ResponseModel findUserIdMsg(Integer userId)throws Exception;

}
