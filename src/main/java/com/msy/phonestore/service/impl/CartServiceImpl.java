package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.CartAndUserAndPhoneAndPhoneDetailetDTO;
import com.msy.phonestore.mapper.CartMapper;
import com.msy.phonestore.mapper.PhoneAssureMapper;
import com.msy.phonestore.mapper.PhoneDetailetMapper;
import com.msy.phonestore.pojo.*;
import com.msy.phonestore.service.ifc.ICartService;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private PhoneAssureMapper phoneAssureMapper;
    //购物车
    @Override
    public ResponseModel findMsgByID(Integer userId) throws Exception {
        MPJLambdaWrapper mpjLambdaWrapper = new MPJLambdaWrapper<>()
                .selectAll(Cart.class)
                .select(PhoneDetailet::getColor, PhoneDetailet::getRam, PhoneDetailet::getStorage, PhoneDetailet::getVersion
                        , PhoneDetailet::getPrice, PhoneDetailet::getScreenSize)
                .select(Phone::getPhoneId, Phone::getPhoneName, Phone::getPhoneImg)
                .select(PhoneCombo::getCombo,PhoneCombo::getComboPrice)
                .select(PhoneAssure::getAssure,PhoneAssure::getAssurePrice,PhoneAssure::getAssureImg)
                .innerJoin(PhoneDetailet.class, PhoneDetailet::getPhoneDetailetId, Cart::getPhoneDetailetId)
                .innerJoin(Phone.class, Phone::getPhoneId, PhoneDetailet::getPhoneId)
                .innerJoin(PhoneCombo.class,PhoneCombo::getComboId,Cart::getComboId)
                .innerJoin(PhoneAssure.class,PhoneAssure::getAssureId,Cart::getAssureId)
                .eq(Cart::getUserId, userId)
                .orderByAsc(Cart::getCartId);

        List<CartAndUserAndPhoneAndPhoneDetailetDTO> cartAndUserAndPhoneDetailetDTOS = cartMapper.selectJoinList(CartAndUserAndPhoneAndPhoneDetailetDTO.class, mpjLambdaWrapper);

        return ResponseModel.success(ResCode.SUCCESS, cartAndUserAndPhoneDetailetDTOS);
    }

    @Override
    public ResponseModel findMsgByIds(Integer[] ids) throws Exception {
        List<CartAndUserAndPhoneAndPhoneDetailetDTO> cartAndUserAndPhoneAndPhoneDetailetDTOList=new ArrayList<>();
        for(int i=0;i<ids.length;i++){
//            System.out.println(ids[i]);
            MPJLambdaWrapper mpjLambdaWrapper = new MPJLambdaWrapper<>()
                    .selectAll(Cart.class)
                    .select(PhoneDetailet::getColor, PhoneDetailet::getRam, PhoneDetailet::getStorage, PhoneDetailet::getVersion
                            , PhoneDetailet::getPrice, PhoneDetailet::getScreenSize)
                    .select(Phone::getPhoneId, Phone::getPhoneName, Phone::getPhoneImg)
                    .select(PhoneCombo::getCombo,PhoneCombo::getComboPrice)
                    .select(PhoneAssure::getAssure,PhoneAssure::getAssurePrice,PhoneAssure::getAssureImg)
                    .innerJoin(PhoneDetailet.class, PhoneDetailet::getPhoneDetailetId, Cart::getPhoneDetailetId)
                    .innerJoin(Phone.class, Phone::getPhoneId, PhoneDetailet::getPhoneId)
                    .innerJoin(PhoneCombo.class,PhoneCombo::getComboId,Cart::getComboId)
                    .innerJoin(PhoneAssure.class,PhoneAssure::getAssureId,Cart::getAssureId)
                    .eq(Cart::getCartId, ids[i]);
            CartAndUserAndPhoneAndPhoneDetailetDTO cartAndUserAndPhoneAndPhoneDetailetDTO = cartMapper.selectJoinOne(CartAndUserAndPhoneAndPhoneDetailetDTO.class, mpjLambdaWrapper);
            cartAndUserAndPhoneAndPhoneDetailetDTOList.add(cartAndUserAndPhoneAndPhoneDetailetDTO);
        }
        return ResponseModel.success(ResCode.SUCCESS,cartAndUserAndPhoneAndPhoneDetailetDTOList);
    }

    @Transactional
    @Override
    public ResponseModel insertCartMsg(Cart cart) throws Exception {

        PhoneDetailet select = phoneDetailetMapper.selectById(cart.getPhoneDetailetId());
        int row=0;
        int row1=0;
        Cart cart1 = cartMapper.selectOne(new QueryWrapper<Cart>()
                .eq("phoneDetailetId",cart.getPhoneDetailetId())
                .eq("userId",cart.getUserId())
                .eq("comboId",cart.getComboId())
                .eq("assureId",cart.getAssureId()));
        System.out.println(cart1);
        if(cart1!=null){
            Cart cart2=new Cart();
            cart2.setCartId(cart1.getCartId());
            cart2.setPhoneDetailetId(cart1.getPhoneDetailetId());
            cart2.setQuantity(cart.getQuantity()+cart1.getQuantity());
            cart2.setUserId(cart1.getUserId());
            cart2.setComboId(cart1.getComboId());
            cart2.setAssureId(cart1.getAssureId());
            row = cartMapper.updateById(cart2);
        }else{
            row1 = cartMapper.insert(cart);
        }
        //查询手机剩余数量后计算
//        int i=1/0;
        if (row > 0 || row1>0) {
            if (select.getQuantity() - cart.getQuantity() >= 0) {

                PhoneDetailet phoneDetailet = new PhoneDetailet();
                phoneDetailet.setPhoneDetailetId(cart.getPhoneDetailetId());
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
    @Transactional
    public ResponseModel updateCartMsgByIDs(Integer[] ids) throws Exception {
        List list = new ArrayList();
        int row = 0, row1 = 0;
        if(ids.length==0){
            int row2=0;
            List<Cart> carts = cartMapper.selectList(null);
            for(Cart cart:carts){
                Cart cart1 = new Cart();
                cart1.setCartId(cart.getCartId());
                cart1.setCartState(0);
                row2 += cartMapper.updateById(cart1);
            }
            if(row2>0){
                return ResponseModel.success(ResCode.SUCCESS);
            }
        }
        for (int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
            Cart cart1 = new Cart();
            cart1.setCartId(ids[i]);
            cart1.setCartState(1);
            row += cartMapper.updateById(cart1);
        }
        List<Cart> carts = cartMapper.selectList(new QueryWrapper<Cart>().notIn("cartId", list));
        if(carts.isEmpty()){
            row1=1;
        }else {
            for (Cart cart : carts) {
                Cart cart2 = new Cart();
                cart2.setCartId(cart.getCartId());
                cart2.setCartState(0);
                row1 += cartMapper.updateById(cart2);
            }
        }
        if (row > 0 && row1 > 0) {
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
    @Transactional
    public ResponseModel total(Integer[] ids) throws Exception {
        double total=0;
        for(int i=0;i<ids.length;i++){
            Cart cart = cartMapper.selectById(ids[i]);
            PhoneDetailet phoneDetailet = phoneDetailetMapper.selectOne(
                    new QueryWrapper<PhoneDetailet>()
                            .eq("phoneDetailetId", cart.getPhoneDetailetId()));
            if(cart.getAssureId()!=0){
                PhoneAssure phoneAssure = phoneAssureMapper.selectById(cart.getAssureId());
                total+=cart.getQuantity()*phoneDetailet.getPrice()+phoneAssure.getAssurePrice();
            }else {
                total+=cart.getQuantity()*phoneDetailet.getPrice();
            }

        }
        return ResponseModel.success(ResCode.SUCCESS,total);
    }
}
