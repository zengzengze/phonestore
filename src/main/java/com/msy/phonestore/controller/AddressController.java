package com.msy.phonestore.controller;

import com.msy.phonestore.pojo.Address;
import com.msy.phonestore.pojo.Cart;
import com.msy.phonestore.service.ifc.IAddressService;
import com.msy.phonestore.service.ifc.ICartService;
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
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @RequestMapping("/addressList")
    public ResponseModel findMsgByMap(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = addressService.findMsgByMap(map);
        return model;
    }

    //
    @RequestMapping("/findAddressById")
    public ResponseModel findMsgById(Integer id)throws Exception{
        System.out.println(id);
        ResponseModel model = addressService.findMsgById(id);
        return model;
    }


    @RequestMapping("/insertAddressMsg")
    public ResponseModel insertAddressMsg(@RequestBody Address address)throws Exception{
        System.out.println(address);
        ResponseModel model = addressService.insertAddressMsg(address);
        return model;
    }

    @RequestMapping("/updateAddressMsg")
    public ResponseModel updateAddressMsg(@RequestBody Address address)throws Exception{
        System.out.println(address);
        ResponseModel model = addressService.updateAddressMsg(address);
        return model;
    }

    @RequestMapping("/updateByAddressMsg")
    public ResponseModel updateByAddressMsg(@RequestBody Address address)throws Exception{
        System.out.println(address);
        ResponseModel model = addressService.updateByAddressMsg(address);
        return model;
    }
//
    @RequestMapping("/setDefaultAddress")
    public ResponseModel setDefault(@RequestBody Address address)throws Exception{
        System.out.println(address);
        ResponseModel model = addressService.setDefault(address);
        return model;
    }

    @RequestMapping("/delAddressMsgByIds")
    public ResponseModel delAddressMsgByIds(@RequestBody Map<String,Integer[]> ids)throws Exception{
        System.out.println(ids.get("ids"));
        ResponseModel model = addressService.delAddressMsgByIds(ids.get("ids"));
        return model;
    }

    @RequestMapping("/updateAddressState")
    public ResponseModel updateAddressState(Integer userId)throws Exception{
        ResponseModel model = addressService.updateAddressState(userId);
        return model;
    }


}
