package com.xu.loadservicewebsocket.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>websocket处理类</p>
 * @author xuhongda on 2018/4/18
 * com.xu.loadservicewebsocket.websocket
 * load-service-parent
 *
 */
@Service
public class MyHandler extends TextWebSocketHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 在线用户列表
     */
    private static final Map<Integer, WebSocketSession> users;
    /**
     * 用户标识
     */
    private static final String CLIENT_ID = "userId";

    static {
        users = new HashMap<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("成功建立连接");
        Integer userId = getClientId(session);
        System.out.println(userId);
        if (userId != null) {
            users.put(userId, session);
            session.sendMessage(new TextMessage("成功建立socket连接"));
            System.out.println(userId);
            System.out.println(session);
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // ...
        logger.info("message",message.getPayload());
        WebSocketMessage message1 = new TextMessage("server:"+message);
        try {
            session.sendMessage(message1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送信息给指定用户
     * @param clientId
     * @param message
     * @return
     */
    public boolean sendMessageToUser(Integer clientId, TextMessage message) {
        if (users.get(clientId) == null){return false;}
        WebSocketSession session = users.get(clientId);
        System.out.println("sendMessage:" + session);
        if (!session.isOpen()){return false;}
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 广播信息
     * @param message
     * @return
     */
    public boolean sendMessageToAllUsers(TextMessage message) {
        boolean allSendSuccess = true;
        Set<Integer> clientIds = users.keySet();
        WebSocketSession session = null;
        for (Integer clientId : clientIds) {
            try {
                session = users.get(clientId);
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                allSendSuccess = false;
            }
        }

        return  allSendSuccess;
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.info("连接出错");
        users.remove(getClientId(session));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("连接已关闭:",status);
        users.remove(getClientId(session));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 获取用户标识
     * @param session
     * @return
     */
    private Integer getClientId(WebSocketSession session) {
        try {
            Integer clientId = (Integer) session.getAttributes().get(CLIENT_ID);
            return clientId;
        } catch (Exception e) {
            return null;
        }
    }

}
