package com.xu.loadserviceweb.controller;

import com.xu.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(student);
        System.out.println(student.getName());
        System.out.println(Student.class.isInstance(student) );
        return student;
    }
    @ResponseBody
    @GetMapping("get")
    public Student get(Student student){
        System.out.println(student);
        return student;
    }

    @RequestMapping("jiqimao")
    public String jiqimao(){
        return "jiqimao";
    }
}
