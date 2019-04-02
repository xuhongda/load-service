package com.xu.loadservicewebsocket.websocket;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.loadservicewebsocket.component.Car;
import com.xu.loadservicewebsocket.component.MySchedule;
import com.xu.loadservicewebsocket.component.People;
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
import java.io.IOException;
import java.util.Date;

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

    @Scheduled(fixedDelay = 7000)
    public void push() throws IOException {
        Date date = new Date();
        Car car  = new Car();
        car.setRfId(date.toString());
        car.setStationId(3);
        car.setFuelType("99");
        car.setPlateNo("粤B88888");
        car.setPrice(7.7);

        String s = objectMapper.writeValueAsString(car);
        logger.info("s={}",s);
        handler.push(new TextMessage(s));
    }
}
