package com.xu.loadservicewebsocket.websocket;

import com.xu.loadservicewebsocket.component.MySchedule;
import com.xu.loadservicewebsocket.config.MyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author xuhongda on 2018/4/18
 * com.xu.loadservicewebsocket.websocket
 * load-service-parent
 */
@Controller
public class SocketController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MyHandler handler;

    @Autowired
    private MySchedule mySchedule;


    @RequestMapping("/login/{userId}")
    public @ResponseBody String login(HttpSession session, @PathVariable("userId") Integer userId) {
        logger.info("登录接口,userId=",userId);
        session.setAttribute("userId", userId);
        System.out.println(session.getAttribute("userId"));
        return "success";
    }

    @RequestMapping("/message")
    public @ResponseBody String sendMessage(Integer id,String message) {
        boolean hasSend = handler.sendMessageToUser(id, new TextMessage(message));
        System.out.println("===========>>>>>"+hasSend);
        return message;
    }

    @GetMapping("date")
    public @ResponseBody Date date(){
        boolean b = handler.sendMessageToUser(3, new TextMessage(new Date().toString()));
        System.out.println(b);
        return  new Date();
    }


    @Scheduled(fixedDelay = 1000)
    public Date x(){
        Date date = new Date();
        System.out.println(date);
        handler.sendMessageToUser(1,new TextMessage(date.toString()));
        return date;
    }
}
