package com.xu.loadservicewebsocket.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;

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
}
