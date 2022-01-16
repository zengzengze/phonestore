package com.msy.phonestore.service.ifc;

import com.msy.phonestore.vo.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/16/17:36
 * @Description:
 */
public interface IOrderAddressService {

    ResponseModel findOrderAddressMsgById(Map<String,Object> map)throws Exception;
}
