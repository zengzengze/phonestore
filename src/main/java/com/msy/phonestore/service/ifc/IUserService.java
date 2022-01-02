package com.msy.phonestore.service.ifc;

import com.msy.phonestore.pojo.Users;
import com.msy.phonestore.vo.ResponseModel;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/24/13:59
 * @Description:
 */

public interface IUserService {

    ResponseModel Login(Users users)throws Exception;





}
