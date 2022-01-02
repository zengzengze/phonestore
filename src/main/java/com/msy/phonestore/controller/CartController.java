package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.Cart;
import com.msy.phonestore.service.ifc.ICartService;
import com.msy.phonestore.service.ifc.IPhoneTypeService;
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
 * @Date: 2021/12/25/15:18
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("/cartList")
    public ResponseModel findMsgByID(Integer userId)throws Exception{
        ResponseModel model = cartService.findMsgByID(userId);
        return model;
    }

    @RequestMapping("/insertCartMsg")
    public ResponseModel insertCartMsg(@RequestBody Cart cart)throws Exception{
//        System.out.println(cart);
        ResponseModel model = cartService.insertCartMsg(cart);
        return model;
    }

    @RequestMapping("/updateCartMsg")
    public ResponseModel updateCartMsg(@RequestBody Cart cart)throws Exception{
        System.out.println(cart);
        ResponseModel model = cartService.updateCartMsg(cart);
        return model;
    }

    @RequestMapping("/deleteCartByIds")
    public ResponseModel deleteCartById(@RequestBody Map<String,Integer[]> ids)throws Exception{
        System.out.println(ids.get("ids"));
        ResponseModel model = cartService.deleteCartById(ids.get("ids"));
        return model;
    }

    @RequestMapping("/totalByIds")
    public ResponseModel total(@RequestBody Map<String,Integer[]> ids)throws Exception{
        System.out.println(ids.get("ids"));
        ResponseModel model = cartService.total(ids.get("ids"));
        return model;
    }

}
