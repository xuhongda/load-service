package com.xu.loadservicewebsocket.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.*;

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
    private static final List<WebSocketSession> sessions;

    static {
        sessions = new ArrayList<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("成功建立连接");
        session.sendMessage(new TextMessage("成功建立socket连接"));
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // ...
        logger.info("message={}", message.getPayload());
        WebSocketMessage message1 = new TextMessage("server:"+message);
        try {
            session.sendMessage(message1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    public void  push(TextMessage message) throws IOException {
        if (!sessions.isEmpty()){
            WebSocketSession session =sessions.get(0);
            if (session.isOpen()) {
                session.sendMessage(message);
            }
        }
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        logger.info("连接出错");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("连接已关闭={}", status);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
