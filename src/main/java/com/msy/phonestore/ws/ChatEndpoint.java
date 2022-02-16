package com.msy.phonestore.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msy.phonestore.pojo.ChatRecord;
import com.msy.phonestore.pojo.Message;
import com.msy.phonestore.service.ifc.IChatRecordService;
import com.msy.phonestore.vo.ApplicationHelper;
import com.msy.phonestore.vo.MessageUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * .
 * Package Name:   com.yyj.ws
 *
 * @author: YYJ
 * Date Time:      2021/5/24 22:40
 */

@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatEndpoint {
    private final static Logger LOGGER = LogManager.getLogger(ChatEndpoint.class);
    /**
     * 用来储存在线用户的容器
     */
    public static Map<Integer, ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();

    public static Map<Integer, ChatEndpoint> onlineAdmins = new ConcurrentHashMap<>();

    public static Map<Integer, Integer> userAndAdmins = new HashMap<>();
    /**
     * 用来给客户端发送消息(通过该对象可以发送消息给指定用户)
     */
    private Session session;
    /**
     * 用来获取在登录成功后，放在httpsession域中存放的username
     */
    private HttpSession httpSession;

    @Resource
    private IChatRecordService chatRecordService = ApplicationHelper.getApplicationContext().getBean(IChatRecordService.class);


    /*建立时调用*/
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) throws JsonProcessingException {

        LOGGER.info("websocket建立连接onOpen！");
        this.session = session;
        //获取httpsession对象
        HttpSession httpSession = (HttpSession) endpointConfig.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        String message = "";
        //获取httpsession域中存放的username对应的值
        Message sendMessage=new Message();
        sendMessage.setIsSystem(true);
        if (httpSession.getAttribute("userId") != null) {
            Integer userId = (Integer) httpSession.getAttribute("userId");
            //存放到onlineUsers中保存·
            onlineUsers.put(userId, this);
            sendMessage.setMessage(getAllOnlineUser());
            message = MessageUtils.getMessage(sendMessage);
            //系统消息推送所有在线用户给客户端
            broadcastMsgToAllOnlineUsers(message);
        } else if (httpSession.getAttribute("adminId") != null) {
            Integer adminId = (Integer) httpSession.getAttribute("adminId");
            onlineAdmins.put(adminId, this);
            sendMessage.setMessage(getAllOnlineAdmin());
            message = MessageUtils.getMessage(sendMessage);
//            broadcastMsgToAllOnlineUsers(message);
            broadcastMsgToAllOnlineAdmins(message);
        }
    }


    /**
     * 接收到客户端发送的数据时调用.
     *
     * @param message 客户端发送的数据
     * @param session session对象
     * @return void
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Message msg = objectMapper.readValue(message, Message.class);
            //获取接收信息的用户
            Integer receiver = msg.getReceiver();

            msg.setIsSystem(false);

            if (msg.getMessageType()) {
                //获取当前登录的用户
//                Integer adminId = (Integer) httpSession.getAttribute("adminId");
                //封装发送的消息
                String sendMessage = MessageUtils.getMessage(msg);
                //发送消息
                onlineUsers.get(receiver).session.getBasicRemote().sendText(sendMessage);
            } else {
                //获取当前登录的用户
//                Integer userId = (Integer) httpSession.getAttribute("userId");
                //封装发送的消息
                Integer userId=msg.getSender();
                String sendMessage = MessageUtils.getMessage(msg);

                //发送消息
                if (receiver != null) {
                    onlineAdmins.get(receiver).session.getBasicRemote().sendText(sendMessage);
                } else {
                    if (userAndAdmins.get(userId) != null && onlineAdmins.get(userAndAdmins.get(userId)) != null) {
                        receiver=userAndAdmins.get(userId);
                        //将未读消息加入数据
                        insertByChatRecordMsg(userId, userAndAdmins.get(userId));
                    } else {
                        if (userAndAdmins.get(userId) != null) {
                            onlineAdmins.remove(userAndAdmins.get(userId));
                        }
                        System.out.println("重新分配客服");
                        Random rd = new Random();
                        int i = rd.nextInt(onlineAdmins.size()) + 0;
                        userAndAdmins.put(userId, (Integer) onlineAdmins.keySet().toArray()[i]);
                        chatRecordService.updateByUserIdAndAdminIdMsg(userId, (Integer) onlineAdmins.keySet().toArray()[i]);
                        //将未读消息加入数据
                        insertByChatRecordMsg(userId, (Integer) onlineAdmins.keySet().toArray()[i]);
                        receiver=(Integer) onlineAdmins.keySet().toArray()[i];
                    }
                    onlineAdmins.get(receiver).session.getBasicRemote().sendText(sendMessage);
                }
            }
        } catch (JsonProcessingException e) {
            LOGGER.error("接收客户端的消息，转换出错了！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭时调用
     */
    @OnClose
    public void onClose(Session session) throws JsonProcessingException {
        String message = "";
        Message sendMessage=new Message();
        sendMessage.setIsSystem(true);
        if (httpSession.getAttribute("userId") != null) {
            Integer userId = (Integer) httpSession.getAttribute("userId");
            //存放到onlineUsers中保存·
            onlineUsers.remove(userId);
            sendMessage.setMessage(getAllOnlineUser());
            message = MessageUtils.getMessage(sendMessage);
            broadcastMsgToAllOnlineUsers(message);
        } else if (httpSession.getAttribute("adminId") != null) {
            Integer adminId = (Integer) httpSession.getAttribute("adminId");
            //存放到onlineUsers中保存·
            onlineAdmins.remove(adminId);

            sendMessage.setMessage(getAllOnlineAdmin());
            message = MessageUtils.getMessage(sendMessage);
            broadcastMsgToAllOnlineAdmins(message);
        }
        //广播
    }

    /**
     * @param message 给客户端发送消息
     * @return void
     */
    private void broadcastMsgToAllOnlineUsers(String message) {
        //所有登录用户名称
        Set<Integer> userIds = onlineUsers.keySet();
        for (Integer userId : userIds) {
            ChatEndpoint chatEndpoint = onlineUsers.get(userId);
            //获取推送对象
            RemoteEndpoint.Basic basicRemote = chatEndpoint.session.getBasicRemote();
            try {
                basicRemote.sendText(message);
            } catch (IOException e) {
                LOGGER.error("广播发送系统消息失败！{}", e);
                e.printStackTrace();
            }
        }
    }

    private void broadcastMsgToAllOnlineAdmins(String message) {
        //所有登录用户名称
        Set<Integer> adminIds = onlineAdmins.keySet();
        for (Integer adminId : adminIds) {
            ChatEndpoint chatEndpoint = onlineAdmins.get(adminId);
            //获取推送对象
            RemoteEndpoint.Basic basicRemote = chatEndpoint.session.getBasicRemote();
            try {
                basicRemote.sendText(message);
            } catch (IOException e) {
                LOGGER.error("广播发送系统消息失败！{}", e);
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取发送给客户端的消息，消息数据示例值：{“systemMsgFlag”: true, "fromName": null, "message": ["Name1", "Name2"]}
     *
     * @return java.lang.Object
     */
    private Object getAllOnlineUser() {
        return ChatEndpoint.onlineUsers.keySet();
    }

    private Object getAllOnlineAdmin() {
        return ChatEndpoint.onlineAdmins.keySet();
    }

    private void insertByChatRecordMsg(Integer userId, Integer adminId) throws Exception {
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setUserId(userId);
        chatRecord.setAdminId(adminId);
        chatRecord.setUnReadCount(1);
        chatRecord.setDateTime(new Date());
        chatRecordService.insertByChatRecordMsg(chatRecord);
    }
}
