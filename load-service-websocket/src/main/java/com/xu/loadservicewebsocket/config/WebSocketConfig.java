package com.xu.loadservicewebsocket.config;

import com.xu.loadservicewebsocket.handler.MyHandler;
import com.xu.loadservicewebsocket.handler.MyHandler2;
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
        registry.addHandler(myHandler(), "/myHandler").addInterceptors(new WebSocketInterceptor()).setAllowedOrigins("http://localhost:8080");
        //解决跨域问题---->>> 设为 * 允许所有
        registry.addHandler(myHandler2(), "/myHandler2").addInterceptors(new WebSocketInterceptor()).setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler myHandler2() {
        return new MyHandler2();
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new MyHandler();
    }

}
