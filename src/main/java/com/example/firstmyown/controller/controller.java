package com.example.firstmyown.controller;

import com.example.firstmyown.model.Users;
import com.example.firstmyown.service.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final MyService serv;
    public controller(MyService service) {
        this.serv = service;
    }

    @RequestMapping("/")
    public String indexSite() {
        time = sdf.format(new Date());
        System.out.println(time + "  SIKER Index oldal betöltés");
        return "index";
    }

    @GetMapping("/register")
    public String registerSite(/*Model model*/) {
        time = sdf.format(new Date());
        System.out.println(time + "  SIKER Register oldal betöltés");

        //model.addAttribute("modell_minta", new Users());
        return "register_site";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Users user_modell) {
        time = sdf.format(new Date());

        Users regisztralt_user = serv.insertUser(user_modell.getNev(), user_modell.getJelszo(), user_modell.getEmail());
        if(regisztralt_user != null) {
            System.out.println(time + "  Felhasználó feltöltése sikeres!");
            return "redirect:/";
        } else {
            System.out.println(time + "  Felhasználó feltöltése sikertelen!");
            return "error_page";
        }
    }

    @PostMapping("/main")
    public String login(@ModelAttribute Users user_modell, Model model) {
        time = sdf.format(new Date());

        Users belepett = serv.selectUser(user_modell.getNev(), user_modell.getJelszo());
        if (belepett != null) {
            model.addAttribute("atadott_nev", belepett.getNev());

            System.out.println(time + " SIKERES bejelentkezés");
            return "main_screen";
        } else {
            System.out.println(time + " SIKERERTELEN bejelentkezés");
            return "error_page";
        }
    }
}


















