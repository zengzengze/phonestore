package com.msy.phonestore.controller;

import com.msy.phonestore.service.ifc.IAdminService;
import com.msy.phonestore.service.ifc.IBuyNowService;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/02/09/16:54
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @RequestMapping("/backLogin")
    public ResponseModel backLogin(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = adminService.backLogin(map);
        return model;
    }
}
