package com.xu.loadservicewebsocket.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.loadservicewebsocket.component.Car;
import com.xu.loadservicewebsocket.config.MyHandler;
import com.xu.loadservicewebsocket.config.MyHandler2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.Date;

/**
 * @author xuhongda on 2018/4/19
 * com.xu.loadservicewebsocket.websocket
 * load-service-parent
 */
@Slf4j
@Controller
public class WebsocketController {

    private ObjectMapper mapper = new ObjectMapper();

    private final MyHandler myHandler;

    private final MyHandler2 myHandler2;

    public WebsocketController(MyHandler myHandler, MyHandler2 myHandler2) {
        this.myHandler = myHandler;
        this.myHandler2 = myHandler2;
    }


    @GetMapping("gid")
    public String xx(){
        return "gid";
    }

    @GetMapping("websocket")
    public String websocket() {
        return "websocket";
    }


    //@Scheduled(fixedDelay = 9000)
    public void push() throws IOException {
        Date date = new Date();
        Car car = new Car();
        car.setRfId(date.toString());
        car.setStationId(3);
        car.setFuelType("99");
        car.setPlateNo("粤B88888");
        car.setPrice(7.7);
        String s = mapper.writeValueAsString(car);
        log.info("s={}", s);
        myHandler2.pushAll(new TextMessage(s));
    }
}
