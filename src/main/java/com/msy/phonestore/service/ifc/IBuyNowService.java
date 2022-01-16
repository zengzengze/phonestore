package com.msy.phonestore.service.ifc;

import com.msy.phonestore.vo.ResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/13/17:07
 * @Description:
 */
public interface IBuyNowService {
    ResponseModel findByMapMsg(Map<String,Object> map)throws Exception;
}
