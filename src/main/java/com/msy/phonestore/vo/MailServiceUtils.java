package com.msy.phonestore.vo; /**
 * @Classname MailServiceUtils
 * @Description TODO
 * @Author 86176
 * @Date 2021-12-17 15:04
 * @Version 1.0
 **/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MailServiceUtils{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender mailSender;

    public ResponseModel  sendMailCode(Map<String,Object> map) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2098382492@qq.com");
        message.setTo((String)map.get("to"));
        message.setSubject("手机商城给你发送了一条验证码,15分钟内有效!");
        message.setText(CreateCode.getCreateCode());
        try {
            mailSender.send(message);
            logger.info("邮件成功发送!");
            return ResponseModel.success(ResCode.SUCCESS, message);
        } catch (MailException e) {
            logger.error("发送邮件错误:", e);
            return ResponseModel.success(ResCode.FAIL);
        }
    }
}