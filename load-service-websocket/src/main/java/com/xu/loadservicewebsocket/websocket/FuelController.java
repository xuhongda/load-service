package com.xu.loadservicewebsocket.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xu.loadservicewebsocket.component.Car;
import com.xu.loadservicewebsocket.component.FuelPayResponse;
import com.xu.loadservicewebsocket.handler.MyHandler;
import com.xu.loadservicewebsocket.handler.MyHandler2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuhongda on 2019/6/28
 * com.xu.loadservicewebsocket.websocket
 * load-service
 */
@Slf4j
@Controller
public class FuelController {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 正在加油队列
     */
    private ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap<>();


    private final MyHandler2 myHandler2;

    public FuelController(MyHandler2 myHandler2) {
        this.myHandler2 = myHandler2;
    }


    @PostMapping("pay")
    @ResponseBody
    public FuelPayResponse pay(@RequestParam(value = "stationId") Integer stationId, @RequestParam(value = "rfId") String id, @RequestParam(value = "refuelPrice") Double price) {

        log.info("stationId = {};price ={}", id, price);
        if (price < 9999) {
            hashMap.remove(stationId);
            log.info("stationId = {}-支付成功：price ={} 元", id, price);
            return new FuelPayResponse(100, "支付成功");
        } else {
            log.info("stationId = {}-支付失败：price ={} 元", id, price);
            return new FuelPayResponse(200, "支付失败");
        }

    }


    @Scheduled(fixedDelay = 9000)
    public void fuel() throws IOException {

        Car car = getCar();
        car.setEventType(1);
        Integer stationId = car.getStationId();
        boolean b = hashMap.containsKey(stationId);
        if (!b) {
            String carInfo = mapper.writeValueAsString(car);
            log.info("carInfo ={}", carInfo);
            myHandler2.pushAll(new TextMessage(carInfo));
            hashMap.put(car.getStationId(), car.getStationId());
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
