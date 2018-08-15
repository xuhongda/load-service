package com.xu.loadservicewebsocket.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author xuhongda on 2018/4/19
 * com.xu.loadservicewebsocket.websocket
 * load-service-parent
 */
@Controller
public class WelcomeController {

    @GetMapping("welcome")
    public String welcome(){
        return "welcome";
    }
    @GetMapping("chat")
    public String chat(){
        return "chat";
    }

    @ResponseBody
    @GetMapping("test")
    public String test(){
        return fuc();
    }

    private String fuc() {
        Date date = new Date();
        return date.toString();
    }


}
