package com.xu.loadservicewebsocket.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.loadservicewebsocket.component.MySchedule;
import com.xu.loadservicewebsocket.handler.MyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author xuhongda on 2018/4/18
 * com.xu.loadservicewebsocket.websocket
 * load-service-parent
 */
@Controller
public class SocketController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MyHandler handler;

    @Autowired
    private MySchedule mySchedule;


    @RequestMapping("/login/{userId}")
    public @ResponseBody String login(HttpSession session, @PathVariable("userId") Integer userId) {
        logger.info("登录接口,userId={}",userId);
        session.setAttribute("userId", userId);
        System.out.println(session.getAttribute("userId"));
        return "success";
    }


}
