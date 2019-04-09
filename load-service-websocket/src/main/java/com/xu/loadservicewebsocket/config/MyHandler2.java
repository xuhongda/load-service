package com.xu.loadservicewebsocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuhongda on 2019/4/9
 * com.xu.loadservicewebsocket.config
 * load-service
 */
@Slf4j
@Service
public class MyHandler2 extends TextWebSocketHandler {

    private Long num = 0L;

    /**
     * 在线用户列表
     */
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    /**
     * open
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        synchronized (num) {
            num++;
        }
        log.info("{}=成功建立连接", num);
        sessions.put(num.toString(), session);
        Map<String, Object> attributes = session.getAttributes();
        log.info("attributes = {}", attributes);
        log.info("sessions = {}", sessions);
    }

    /**
     * onmessage
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // ...
        log.info("message={}", message.getPayload());

        try {
            session.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * close
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("连接已关闭={}", status);

        List<String> removeSessions = new ArrayList();

        Set<Map.Entry<String, WebSocketSession>> entries = sessions.entrySet();
        for (Map.Entry<String, WebSocketSession> o : entries) {
            String key = o.getKey();
            WebSocketSession value = o.getValue();
            if (value == session) {
                removeSessions.add(key);
            }
        }

        for (String key : removeSessions) {
            log.info("被删除的session num = {}", key);
            sessions.remove(key);
        }
    }

    public void push(TextMessage textMessage) throws IOException {

        if (!sessions.isEmpty()) {
            int size = sessions.size();

            Random random = new Random(size + 1);
            Integer i = random.nextInt(size + 1);
            log.info("随机数为={}", i);
            WebSocketSession webSocketSession = sessions.get(i.toString());
            if (webSocketSession != null) {
                log.info("在线session 数量 = {}", i);
                log.info("推送-->>>num = {}", i);
                handleTextMessage(webSocketSession, textMessage);
            }
        }
    }

    /**
     * 群发
     */
    public void pushAll(TextMessage textMessage) throws IOException {

        if (!sessions.isEmpty()) {
            Iterator<Map.Entry<String, WebSocketSession>> iterator = sessions.entrySet().iterator();
            while (iterator.hasNext()) {
                WebSocketSession session = iterator.next().getValue();
                handleTextMessage(session, textMessage);
                log.info("session={}", session);
            }
        }
    }
}
