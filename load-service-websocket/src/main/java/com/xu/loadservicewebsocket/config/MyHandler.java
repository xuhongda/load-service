package com.xu.loadservicewebsocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.loadservicewebsocket.component.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

/**
 * <p>websocket处理类</p>
 *
 * @author xuhongda on 2018/4/18
 * com.xu.loadservicewebsocket.websocket
 * load-service-parent
 */
@Service
public class MyHandler extends TextWebSocketHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ObjectMapper mapper = new ObjectMapper();
    /**
     * 在线用户列表
     */
    private static  List<WebSocketSession> sessions;

    static {
        sessions = new ArrayList<>();
    }

    /**
     * open
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("成功建立连接");
        session.sendMessage(new TextMessage("成功建立socket连接"));
        sessions.add(session);
    }

    /**
     * onmessage
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // ...
        logger.info("message={}", message.getPayload());

        Car car = mapper.readValue(message.getPayload(), Car.class);
        logger.info("car = {}", car.getDeviceId());
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
        logger.info("连接已关闭={}", status);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.info("连接出错");
    }

    public void push(TextMessage textMessage) throws IOException {
        if (!sessions.isEmpty()) {
            handleTextMessage(sessions.get(0), textMessage);
        }
    }
}
