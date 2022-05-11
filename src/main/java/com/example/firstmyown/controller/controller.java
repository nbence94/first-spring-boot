package com.example.firstmyown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Controller
public class controller {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    String time;

    @RequestMapping("/")
    public String indexSite() {
        time = sdf.format(new Date());
        System.out.println(time + "  SIKER Index oldal betöltés");
        return "index";
    }

    @GetMapping("/register")
    public String registerSite() {
        time = sdf.format(new Date());
        System.out.println(time + "  SIKER Register oldal betöltés");
        return "register_site";
    }

    @GetMapping("/main")
    public String mainSite() {
        time = sdf.format(new Date());
        System.out.println(time + "  SIKER Főoldal betöltés");
        return "main_site";
    }
}
