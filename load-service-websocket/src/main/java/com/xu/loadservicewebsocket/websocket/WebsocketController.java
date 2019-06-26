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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuhongda on 2018/4/19
 * com.xu.loadservicewebsocket.websocket
 * load-service-parent
 */
@Slf4j
@Controller
public class WebsocketController {

    /**
     * 正在加油队列
     */
    private ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap();


    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

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

    @GetMapping("test")
    public String test(){
        return "test";
    }

    @GetMapping("refuel-new")
    public String refuel(){
        return "refuel-new";
    }

    @GetMapping("websocket")
    public String websocket() {
        return "websocket";
    }


    @Scheduled(fixedDelay = 9000)
    public void fuel() throws IOException {

        Car car = getCar();
        car.setEventType(1);
        Integer stationId = car.getStationId();
        boolean b = hashMap.containsKey(stationId);
        if (!b) {
            hashMap.put(car.getStationId(), car.getStationId());
            String s = mapper.writeValueAsString(car);
            log.info("s={}", s);
            myHandler2.pushAll(new TextMessage(s));
            log.info("{} 站台= 开始加油", stationId);
        } else {
            log.info("{} 站台= 正在加油，不可以继续加油", stationId);
        }

    }


    @Scheduled(fixedDelay = 19000)
    public void stopFuel() throws IOException {
        Car car = getCar();
        car.setEventType(0);
        Integer stationId = car.getStationId();
        boolean b = hashMap.containsKey(stationId);
        if (b) {
            String s = mapper.writeValueAsString(car);
            log.info("s={}", s);
            myHandler2.pushAll(new TextMessage(s));
            //真正的应该支付完成后再把集合正在加油的stationId 删除
            hashMap.remove(stationId);
            log.info("{} 站台= 停止加油", stationId);
        } else {
            log.info("{} 站台= 未在加油，不可以停止", stationId);
        }

    }


    private Car getCar() {
        Car car = new Car();
        Date date = new Date();
        car.setRfId(UUID.randomUUID().toString());
        int v = (int) (Math.random() * 8 + 1);
        log.info("setStationId === {}", v);
        car.setStationId(v);
        car.setFuelType("99");
        car.setPlateNo("粤B8888" + v);
        car.setPrice(7.7);
        return car;
    }
}
