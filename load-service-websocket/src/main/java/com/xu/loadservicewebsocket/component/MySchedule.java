package com.xu.loadservicewebsocket.component;

import com.xu.loadservicewebsocket.config.MyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.util.Date;

/**
 * @author xuhongda on 2018/8/15
 * com.xu.loadservicewebsocket.component
 * load-service-parent
 */
@Component
public class MySchedule  {

    /**
     * 用构造器方式注入
     */
   /* private MyHandler myHandler;
    @Autowired
    public MySchedule(MyHandler myHandler){
        this.myHandler = myHandler;
    }*/


   /* @Scheduled(fixedDelay = 1000)
    public Date x(){
        Date date = new Date();
        System.out.println(date);
       // myHandler.sendMessageToUser(1,new TextMessage(date.toString()));
        return date;
    }*/


}
