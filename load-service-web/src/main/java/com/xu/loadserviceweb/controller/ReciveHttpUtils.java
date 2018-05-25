package com.xu.loadserviceweb.controller;

import com.xu.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xuhongda on 2018/5/24
 * com.xu.loadserviceweb.controller
 * load-service-parent
 */
@Controller
@RequestMapping("http")
public class ReciveHttpUtils {
    @ResponseBody
    @PostMapping("post")
    public Student post(@RequestBody Student student){
        System.out.println(student == null);
        System.out.println(student.getName());
        return student;
    }

    @RequestMapping("jiqimao")
    public String jiqimao(){
        return "jiqimao";
    }
}
