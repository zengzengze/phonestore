package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.CartAndUserAndPhoneAndPhoneDetailetDTO;
import com.msy.phonestore.mapper.CartMapper;
import com.msy.phonestore.mapper.PhoneDetailetMapper;
import com.msy.phonestore.pojo.*;
import com.msy.phonestore.service.ifc.ICartService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2021/11/24/15:50
 * @Description:
 */
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private PhoneDetailetMapper phoneDetailetMapper;

    //购物车
    @Override
    public ResponseModel findMsgByID(Integer userId) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper = new MPJLambdaWrapper<>()
                .selectAll(Cart.class)
                .select(PhoneDetailet::getColor, PhoneDetailet::getRam, PhoneDetailet::getStorage, PhoneDetailet::getVersion
                        , PhoneDetailet::getPrice, PhoneDetailet::getScreenSize)
                .select(Phone::getPhoneId, Phone::getPname, Phone::getPhoneImg)
                .innerJoin(PhoneDetailet.class, PhoneDetailet::getPDetailetId, Cart::getpDetailetId)
                .innerJoin(Phone.class, Phone::getPhoneId, PhoneDetailet::getPhoneId)
                .eq(Cart::getUserId, userId);

        List<CartAndUserAndPhoneAndPhoneDetailetDTO> cartAndUserAndPhoneDetailetDTOS = cartMapper.selectJoinList(CartAndUserAndPhoneAndPhoneDetailetDTO.class, mpjLambdaWrapper);

        return ResponseModel.success(ResCode.SUCCESS, cartAndUserAndPhoneDetailetDTOS);
    }

    @Transactional
    @Override
    public ResponseModel insertCartMsg(Cart cart) throws Exception {

        PhoneDetailet select = phoneDetailetMapper.selectById(cart.getpDetailetId());
        int row=0;
        int row1=0;
        Cart cart1 = cartMapper.selectOne(new QueryWrapper<Cart>()
                .eq("pDetailetId",cart.getpDetailetId())
                .eq("userId",cart.getUserId()));
        System.out.println(cart1);
        if(cart1!=null){
            Cart cart2=new Cart();
            cart2.setCartId(cart1.getCartId());
            cart2.setpDetailetId(cart1.getpDetailetId());
            cart2.setQuantity(cart.getQuantity()+cart1.getQuantity());
            cart2.setUserId(cart1.getUserId());
            row = cartMapper.updateById(cart2);
        }else{
            row1 = cartMapper.insert(cart);
        }
        //查询手机剩余数量后计算
//        int i=1/0;
        if (row > 0 || row1>0) {
            if (select.getQuantity() - cart.getQuantity() >= 0) {

                PhoneDetailet phoneDetailet = new PhoneDetailet();
                phoneDetailet.setPDetailetId(cart.getpDetailetId());
                phoneDetailet.setQuantity(select.getQuantity() - cart.getQuantity());
                phoneDetailet.setPrice(select.getPrice());
                int row2 = phoneDetailetMapper.updateById(phoneDetailet);
                if (row2 > 0) {
                    return ResponseModel.success(ResCode.SUCCESS);
                }
            } else {
                return ResponseModel.success(ResCode.FAIL, "库存数量不足");
            }
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel updateCartMsg(Cart cart) throws Exception {
        int row = cartMapper.updateById(cart);
        if (row > 0) {
            return ResponseModel.success(ResCode.SUCCESS);
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel deleteCartById(Integer[] ids) throws Exception {
        int row = 0;
        for (int i = 0; i < ids.length; i++) {
            int row1 = cartMapper.deleteById(ids[i]);
            if (row1 > 0) {
                row++;
            }
        }
        if (row > 0) {
            return ResponseModel.success(ResCode.SUCCESS);

        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    @Override
    public ResponseModel total(Integer[] ids) throws Exception {

        double total=0;
        for(int i=0;i<ids.length;i++){
            Cart cart = cartMapper.selectById(ids[i]);
            PhoneDetailet phoneDetailet = phoneDetailetMapper.selectOne(
                    new QueryWrapper<PhoneDetailet>()
                            .eq("pDetailetId", cart.getpDetailetId()));
            total+=cart.getQuantity()*phoneDetailet.getPrice();
        }
        return ResponseModel.success(ResCode.SUCCESS,String.format("%.2f", total));
    }
}
