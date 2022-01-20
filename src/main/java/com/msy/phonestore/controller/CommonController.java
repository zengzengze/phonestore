package com.msy.phonestore.controller;

import com.msy.phonestore.service.ifc.IDeliveryToolsInfoService;
import com.msy.phonestore.vo.CreateCode;
import com.msy.phonestore.vo.MailServiceUtils;
import com.msy.phonestore.vo.ResCode;
import com.msy.phonestore.vo.ResponseModel;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.msy.phonestore.vo.CreateCode.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zengfanjing
 * @Date: 2022/01/13/21:03
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private IDeliveryToolsInfoService deliveryToolsInfoService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private MailServiceUtils mailServiceUtils;

    @RequestMapping("/findDeliveryTools")
    public ResponseModel deliveryTools(@RequestBody Map<String,Object> map)throws Exception{
        ResponseModel model = deliveryToolsInfoService.findDeliveryMsg(map);
        return model;
    }

    //QQ邮箱验证码使用
    @RequestMapping("/sendEmailCode")
    public ResponseModel sendEmailCode(@RequestBody Map<String,Object> map) throws Exception {
        redisTemplate.opsForValue().set((String) map.get("to"),CreateCode.getCreateCode(),15, TimeUnit.MINUTES);
        ResponseModel model = mailServiceUtils.sendMailCode(map);
        return model;
    }
    //验证码对比
    @RequestMapping("/compareCode")
    public ResponseModel compareCode(@RequestBody Map<String,Object> map)throws Exception {
        //根据邮箱帐号取出验证码
        String o = (String) redisTemplate.opsForValue().get(map.get("to"));
        if(o!=null){
            if (o.equals(map.get("code"))){
                redisTemplate.delete((String) map.get("to"));
                return ResponseModel.success(ResCode.SUCCESS);
            }
        }else {
            return ResponseModel.success(ResCode.FAIL,"验证码已失效请重新获取验证码!");
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

    //手机验证码
    @RequestMapping("/getPhoneCode")
    public ResponseModel getPhoneCode(String phone){
        try {
            Credential cred = new Credential("AKIDLSN3btY7wUorPP9SBdojNTJuKI7XYZkn", "2ymaEiwYr43o35MkLiohOJ0c6NhqnZu1");
            ClientProfile clientProfile = new ClientProfile();
            SmsClient client = new SmsClient(cred,"",clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            String appid = "1400290787";
            req.setSmsSdkAppid(appid);
            String sign = "会写程序的猪";
            req.setSign(sign);
            String templateID = "482510";
            req.setTemplateID(templateID);
            String[] phoneNumbers = {"+86"+phone};
            req.setPhoneNumberSet(phoneNumbers);

            String code = getCreateCode();
            String[] templateParams = {code};
            req.setTemplateParamSet(templateParams);
            SendSmsResponse res = client.SendSms(req);

            // 输出 JSON 格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));
            System.out.println(res);
            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
//            System.out.println(res.getRequestId());
            if(res.getSendStatusSet()[0].getCode().equals("Ok")){
                return ResponseModel.success(ResCode.SUCCESS,code);
            }
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseModel.fail(ResCode.ERROR);
    }
}
