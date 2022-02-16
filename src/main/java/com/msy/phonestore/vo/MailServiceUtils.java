package com.msy.phonestore.vo; /**
 * @Classname MailServiceUtils
 * @Description TODO
 * @Author 86176
 * @Date 2021-12-17 15:04
 * @Version 1.0
 **/
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Component
public class MailServiceUtils{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private JavaMailSender mailSender;

    @Autowired
    private JavaMailSenderImpl javaMailSenderImpl;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public ResponseModel  sendMailCode(Map<String,Object> map) throws Exception {
//        SimpleMailMessage message = new SimpleMailMessage();
        Properties properties=new Properties();
        properties.setProperty("mail.host","smtp.qq.com");//设置qq邮件服务器
        properties.setProperty("mail.transport.protocol","smtp");//邮件发送协议
        properties.setProperty("mail.smtp.auth","true");//需要验证用户名密码

        //关于qq邮箱，还要设置ssl加密，加上以上代码
        MailSSLSocketFactory sf=new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.ssl.socketFactory",sf);

        Session session=Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名，授权码
                return new PasswordAuthentication("2098382492@qq.com","uheqqheoenitdjdj");
            }
        });

        //开启Session的debug模式，可以查看到程序发送Email的运行装填
        session.setDebug(true);

        //通过session得到transport对象
        Transport ts=session.getTransport();
        ts.connect("smtp.qq.com","2098382492");

        MimeMessage message = javaMailSenderImpl.createMimeMessage();
        message.setFrom(new InternetAddress("2098382492@qq.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress((String) map.get("to")));
        message.setSubject("手机商城给你发送了一条验证码,15分钟内有效!");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String mailCode=CreateCode.getCreateCode();
        String text="<p style='font-size: 20px;font-weight:bold;'>尊敬的："+map.get("to")+"，您好！</p>"
                + "<p style='text-indent:2em; font-size: 20px;'>欢迎注册手机商城网，您本次的注册码是 "
                + "<span style='font-size:30px;font-weight:bold;color:red'>" + mailCode + "</span>，15分钟之内有效，请尽快使用！</p>"
                + "<p style='text-align:right; padding-right: 20px;'"
                + "<a href='http://www.hyycinfo.com' style='font-size: 18px'>手机商城</a></p>"
                + "<span style='font-size: 18px; float:right; margin-right: 60px;'>" + sdf.format(new Date()) + "</span>";
        message.setContent(text,"text/html;charset=UTF-8");

        redisTemplate.opsForValue().set((String) map.get("to"),mailCode,15, TimeUnit.MINUTES);

        try {
            ts.sendMessage(message,message.getAllRecipients());
            logger.info("邮件成功发送!");
            return ResponseModel.success(ResCode.SUCCESS, mailCode);
        } catch (MailException e) {
            logger.error("发送邮件错误:", e);
            return ResponseModel.success(ResCode.FAIL);
        }
    }
}