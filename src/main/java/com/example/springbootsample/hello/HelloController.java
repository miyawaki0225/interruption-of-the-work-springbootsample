package com.example.springbootsample.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    //GET
    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }

    //POST
    @PostMapping("/hello")
    public String postRequest(@RequestParam("text1") String str,Model model){
        model.addAttribute("sample",str);
        return "hello/response";
    }
}
