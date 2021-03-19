package kr.watw.jaeun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("index")
    public String index(){

        System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("layoutTest")
    public String layoutTest() {
        return "layoutTest";
    }

    @GetMapping("layoutTest2")
    public String layoutTest2() {
        return "layoutTest2";
    }
}