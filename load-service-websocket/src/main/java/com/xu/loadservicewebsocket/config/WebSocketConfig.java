package com.xu.loadservicewebsocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author xuhongda on 2018/4/18
 * com.xu.loadservicewebsocket.websocket
 * load-service-parent
 * <p>websocket 配置类</p>
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/myHandler").addInterceptors(new WebSocketInterceptor());
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new MyHandler();
    }

}
