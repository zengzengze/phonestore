package com.msy.phonestore.controller;

import com.alibaba.fastjson.JSON;
import com.msy.phonestore.pojo.FileData;
import com.msy.phonestore.pojo.Message;
import com.msy.phonestore.vo.*;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;
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


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private MailServiceUtils mailServiceUtils;

    //QQ邮箱验证码使用
    @RequestMapping("/sendEmailCode")
    public ResponseModel sendEmailCode(@RequestBody Map<String,Object> map) throws Exception {

        ResponseModel model = mailServiceUtils.sendMailCode(map);
        return model;
    }
    //验证码对比
    @RequestMapping("/compareCode")
    public ResponseModel compareCode(@RequestBody Map<String,Object> map)throws Exception {
        //根据邮箱帐号取出验证码
        String mailCode = (String) redisTemplate.opsForValue().get(map.get("to"));
        if(mailCode!=null){
            if (mailCode.equals(map.get("code"))){
                redisTemplate.delete((String) map.get("to"));
                return ResponseModel.success(ResCode.SUCCESS);
            }
        }else {
            return ResponseModel.success(ResCode.FAIL,"验证码已失效请重新获取验证码!");
        }
        return ResponseModel.fail(ResCode.FAIL);
    }

//    //手机验证码
//    @RequestMapping("/getPhoneCode")
//    public ResponseModel getPhoneCode(String phone){
//        try {
//            Credential cred = new Credential("AKIDLSN3btY7wUorPP9SBdojNTJuKI7XYZkn", "2ymaEiwYr43o35MkLiohOJ0c6NhqnZu1");
//            ClientProfile clientProfile = new ClientProfile();
//            SmsClient client = new SmsClient(cred,"",clientProfile);
//            SendSmsRequest req = new SendSmsRequest();
//            String appid = "1400290787";
//            req.setSmsSdkAppid(appid);
//            String sign = "会写程序的猪";
//            req.setSign(sign);
//            String templateID = "482510";
//            req.setTemplateID(templateID);
//            String[] phoneNumbers = {"+86"+phone};
//            req.setPhoneNumberSet(phoneNumbers);
//
//            String code = getCreateCode();
//            String[] templateParams = {code};
//            req.setTemplateParamSet(templateParams);
//            SendSmsResponse res = client.SendSms(req);
//
//            // 输出 JSON 格式的字符串回包
//            System.out.println(SendSmsResponse.toJsonString(res));
//            System.out.println(res);
//            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
////            System.out.println(res.getRequestId());
//            if(res.getSendStatusSet()[0].getCode().equals("Ok")){
//                return ResponseModel.success(ResCode.SUCCESS,code);
//            }
//        } catch (TencentCloudSDKException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseModel.fail(ResCode.ERROR);
//    }


    @RequestMapping("/addOnlineUsers")
    public Integer addOnlineUsers(@RequestBody Map<String,Object> map, HttpSession session) throws Exception {
        session.setAttribute("userId",Integer.parseInt((String) map.get("userId")));
        return (Integer) session.getAttribute("userId");
    }

    @RequestMapping("/addOnlineAdmins")
    public Integer addOnlineAdmins(@RequestBody Map<String,Object> map, HttpSession session) throws Exception {
        session.setAttribute("adminId", map.get("adminId"));
        return (Integer) session.getAttribute("adminId");
    }

    //手机验证码
    @RequestMapping("/getPhoneCode")
    public ResponseModel getPhoneCode(String phone) throws Exception {
        String host = "https://intlsms.market.alicloudapi.com";
        String path = "/comms/sms/sendmsgall";
        String method = "POST";
        String appcode = "83dfbd3085a44173856733d0ce36815e";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();

        StringBuffer createCode=new StringBuffer(getCreateCode());
        String code=createCode.toString();
        createCode.append(",5");

        bodys.put("callbackUrl", "http://test.dev.esandcloud.com");
        bodys.put("channel", "0");
        StringBuffer phoneNumber=new StringBuffer("+86");
        phoneNumber.append(phone);
        bodys.put("mobile", phoneNumber.toString());
        bodys.put("templateID", "0000000");
        bodys.put("templateParamSet", createCode.toString());

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            return ResponseModel.success(ResCode.SUCCESS,code);
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseModel.fail(ResCode.ERROR);
    }


    @RequestMapping("/saveChatRecord")
    public void saveChatRecord(@RequestBody Map<String, Object> map)throws Exception{
        Message message = JSON.parseObject(JSON.toJSONString(map.get("message")), Message.class);
        System.out.println(message);
        FileStreamUtils.writer(String.valueOf(map.get("textName")),message);
    }

    @RequestMapping("/findChatRecord")
    public List<Message> findChatRecord(Integer userId)throws Exception{
        return FileStreamUtils.reader(String.valueOf(userId));
    }
}
