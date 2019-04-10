package com.xu.loadserviceweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xuhongda on 2019/4/10
 * com.xu.loadserviceweb.controller
 * load-service
 */
@Controller
public class NavigationConyroller {

    @GetMapping("websocket")
    public String web() {
        return "websocket";
    }
}
