package com.msy.phonestore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.msy.phonestore.dto.CartAndUserAndPhoneAndPhoneDetailDTO;
import com.msy.phonestore.mapper.*;
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
    private PhoneDetailMapper phoneDetailMapper;

    @Autowired
    private PhoneAssureMapper phoneAssureMapper;

    @Autowired
    private PhoneComboMapper phoneComboMapper;
    @Autowired
    private CommodityMapper commodityMapper;

    //购物车
    @Override
    public ResponseModel findMsgByID(Integer userId) throws Exception {

        List<CartAndUserAndPhoneAndPhoneDetailDTO> DTOS = new ArrayList<>();

        List<Cart> cartList = cartMapper.selectList(new QueryWrapper<Cart>().eq("userId", userId).orderByAsc("cartId"));
        for (Cart cart : cartList) {
            MPJLambdaWrapper mpjLambdaWrapper = new MPJLambdaWrapper<>()
                    .selectAll(Cart.class)
                    .select(PhoneDetail::getColor, PhoneDetail::getRam, PhoneDetail::getStorage, PhoneDetail::getVersion
                            , PhoneDetail::getPrice, PhoneDetail::getScreenSize)
                    .select(Phone::getPhoneId, Phone::getPhoneName, Phone::getPhoneImg)
                    .select(PhoneCombo::getCombo, PhoneCombo::getComboPrice, PhoneCombo::getCommodityId)
                    .innerJoin(PhoneDetail.class, PhoneDetail::getPhoneDetailId, Cart::getPhoneDetailId)
                    .innerJoin(Phone.class, Phone::getPhoneId, PhoneDetail::getPhoneId)
                    .innerJoin(PhoneCombo.class, PhoneCombo::getComboId, Cart::getComboId)
                    .eq(Cart::getCartId, cart.getCartId());
            PhoneDetail phoneDetail = phoneDetailMapper.selectById(cart.getPhoneDetailId());
            CartAndUserAndPhoneAndPhoneDetailDTO DTO = cartMapper.selectJoinOne(CartAndUserAndPhoneAndPhoneDetailDTO.class, mpjLambdaWrapper);

            List<PhoneAssure> phoneAssureList = phoneAssureMapper.selectList(new QueryWrapper<PhoneAssure>().eq("phoneId", phoneDetail.getPhoneId()));
            if (!phoneAssureList.isEmpty()) {
                DTO.setPhoneAssureList(phoneAssureList);
            }
            if (DTO.getCommodityId() != null && DTO.getCommodityId() != 0) {
                Commodity commodity = commodityMapper.selectById(DTO.getCommodityId());
                DTO.setCommodity(commodity);
            }
            DTOS.add(DTO);
        }
        return ResponseModel.success(ResCode.SUCCESS, DTOS);
    }

    @Override
    public ResponseModel findMsgByIds(Integer[] ids) throws Exception {
        List<CartAndUserAndPhoneAndPhoneDetailDTO> DTOList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {

            MPJLambdaWrapper mpjLambdaWrapper = new MPJLambdaWrapper<>()
                    .selectAll(Cart.class)
                    .select(PhoneDetail::getColor, PhoneDetail::getRam, PhoneDetail::getStorage, PhoneDetail::getVersion
                            , PhoneDetail::getPrice, PhoneDetail::getScreenSize)
                    .select(Phone::getPhoneId, Phone::getPhoneName, Phone::getPhoneImg)
                    .select(PhoneCombo::getCombo, PhoneCombo::getComboPrice, PhoneCombo::getCommodityId)
                    .innerJoin(PhoneDetail.class, PhoneDetail::getPhoneDetailId, Cart::getPhoneDetailId)
                    .innerJoin(Phone.class, Phone::getPhoneId, PhoneDetail::getPhoneId)
                    .innerJoin(PhoneCombo.class, PhoneCombo::getComboId, Cart::getComboId)
                    .eq(Cart::getCartId, ids[i]);
            CartAndUserAndPhoneAndPhoneDetailDTO DTO = cartMapper.selectJoinOne(CartAndUserAndPhoneAndPhoneDetailDTO.class, mpjLambdaWrapper);
            if (DTO.getCommodityId() != null && DTO.getCommodityId() != 0) {
                Commodity commodity = commodityMapper.selectById(DTO.getCommodityId());
                DTO.setCommodity(commodity);
            }
            Cart cart = cartMapper.selectById(ids[i]);
            if(cart.getAssureId()!=null){
                PhoneAssure phoneAssure = phoneAssureMapper.selectById(cart.getAssureId());
                DTO.setPhoneAssure(phoneAssure);
            }
            DTOList.add(DTO);
        }
        return ResponseModel.success(ResCode.SUCCESS, DTOList);
    }

    @Transactional
    @Override
    public ResponseModel insertCartMsg(Cart cart) throws Exception {

        PhoneDetail select = phoneDetailMapper.selectById(cart.getPhoneDetailId());
        int row = 0;
        int row1 = 0;
        Cart cart1 = cartMapper.selectOne(new QueryWrapper<Cart>()
                .eq("phoneDetailId", cart.getPhoneDetailId())
                .eq("userId", cart.getUserId())
                .eq("comboId", cart.getComboId())
                .eq("assureId", cart.getAssureId()));

        System.out.println(cart1);
        if (cart1 != null) {
            Cart cart2 = new Cart();
            cart2.setCartId(cart1.getCartId());
            cart2.setPhoneDetailId(cart1.getPhoneDetailId());
            cart2.setQuantity(cart.getQuantity() + cart1.getQuantity());
            cart2.setUserId(cart1.getUserId());
            cart2.setComboId(cart1.getComboId());
            cart2.setAssureId(cart1.getAssureId());
            row = cartMapper.updateById(cart2);
        } else {
            row1 = cartMapper.insert(cart);
        }
        //查询手机剩余数量后计算
//        int i=1/0;
        if (row > 0 || row1 > 0) {
            if (select.getQuantity() - cart.getQuantity() >= 0) {

                PhoneDetail phoneDetail = new PhoneDetail();
                phoneDetail.setPhoneDetailId(cart.getPhoneDetailId());
                phoneDetail.setQuantity(select.getQuantity() - cart.getQuantity());
                phoneDetail.setPrice(select.getPrice());
                int row2 = phoneDetailMapper.updateById(phoneDetail);
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
        if (ids.length == 0) {
            int row2 = 0;
            List<Cart> carts = cartMapper.selectList(null);
            for (Cart cart : carts) {
                Cart cart1 = new Cart();
                cart1.setCartId(cart.getCartId());
                cart1.setCartState(0);
                row2 += cartMapper.updateById(cart1);
            }
            if (row2 > 0) {
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
        if (carts.isEmpty()) {
            row1 = 1;
        } else {
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
        double total = 0;
        for (int i = 0; i < ids.length; i++) {
            Cart cart = cartMapper.selectById(ids[i]);
            PhoneDetail phoneDetail = phoneDetailMapper.selectOne(
                    new QueryWrapper<PhoneDetail>()
                            .eq("phoneDetailId", cart.getPhoneDetailId()));
            total += cart.getQuantity() * phoneDetail.getPrice();
            if (cart.getAssureId() != 0) {
                PhoneAssure phoneAssure = phoneAssureMapper.selectById(cart.getAssureId());
                total += cart.getQuantity() * phoneAssure.getAssurePrice();
            }
            if (cart.getComboId() != 0) {
                PhoneCombo phoneCombo = phoneComboMapper.selectById(cart.getComboId());
                total += cart.getQuantity() * phoneCombo.getComboPrice();
            }
        }
        return ResponseModel.success(ResCode.SUCCESS, total);
    }
}
