package com.xu.loadservicecrawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xuhongda on 2018/5/15
 * com.xu.loadservicecrawler.controller
 * load-service-parent
 */
@Controller
@RequestMapping("Music")
public class MusicCrawlerController {

    @RequestMapping("listen")
    public void listen(@RequestParam(value ="keyWords") String keyWords){

    }

    @RequestMapping("download")
    public void download(@RequestParam(value ="keyWords") String keyWords){

    }


}
