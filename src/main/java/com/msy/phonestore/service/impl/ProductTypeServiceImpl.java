package com.msy.phonestore.service.impl;

import com.msy.phonestore.mapper.ProductTypeMapper;
import com.msy.phonestore.pojo.ProductType;
import com.msy.phonestore.service.ifc.IProductTypeService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/30/10:48
 * @Description:
 */
@Service
public class ProductTypeServiceImpl implements IProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public ResponseModel findProductTypeList(Map<String, Object> map) throws Exception {
        List<ProductType> productTypeList = productTypeMapper.selectByMap(map);
        return ResponseModel.success(ResCode.SUCCESS,productTypeList);
    }
}
