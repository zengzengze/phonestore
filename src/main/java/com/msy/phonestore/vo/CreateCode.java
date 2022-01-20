package com.msy.phonestore.vo;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/17/21:37
 * @Description:
 */
public class CreateCode {
    public static String getCreateCode()throws Exception{
        String str="012345678998765432101234567890";
        Random rn=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<6;i++){
            sb.append(str.charAt(rn.nextInt(str.length())));
        }
        return sb.toString();
    }
}
