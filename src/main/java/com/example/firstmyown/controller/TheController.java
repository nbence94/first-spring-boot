package com.example.firstmyown.controller;

import com.example.firstmyown.model.Users;
import com.example.firstmyown.model.Vocabularies;
import com.example.firstmyown.model.Words;
import com.example.firstmyown.service.UserService;
import com.example.firstmyown.service.VocabularyService;
import com.example.firstmyown.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class TheController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    String time;

    String login_name;
    Integer actual_user_id;

    List<Vocabularies> user_vocabulary_list;
    List<Words> word_list;

    private final UserService user_service;
    private final VocabularyService voc_serv;
    private final WordService word_service;
    public TheController(UserService service, VocabularyService vocabulary_service, WordService wservice) {
        this.user_service = service;
        this.voc_serv = vocabulary_service;
        this.word_service = wservice;
    }

    @GetMapping("/")
    public String indexSite(Model model) {
        time = sdf.format(new Date());
        System.out.println(time + "  SIKER Index oldal betöltés");
        model.addAttribute("loginRequest", new Users());
        return "index";
    }

    @GetMapping("/register")
    public String registerSite(Model model) {
        time = sdf.format(new Date());
        System.out.println(time + "  SIKER Register oldal betöltés");

        model.addAttribute("registerRequest", new Users());
        return "register_site";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Users user_modell) {
        time = sdf.format(new Date());
        Users regisztralt_user = user_service.insertUser(user_modell.getNev(), user_modell.getJelszo(), user_modell.getEmail());
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

        Users belepett = user_service.selectUser(user_modell.getNev(), user_modell.getJelszo());
        if (belepett != null) {
            login_name = belepett.getNev();
            actual_user_id = belepett.getId();
            model.addAttribute("atadott_nev", login_name);


            user_vocabulary_list = voc_serv.megjelenites(actual_user_id);
            model.addAttribute("given_list", user_vocabulary_list);

            System.out.println(time + " SIKERES bejelentkezés");
            return "main_screen";
        } else {
            System.out.println(time + " SIKERTELEN bejelentkezés");
            return "error_page";
        }
    }

    @GetMapping("/new")
    public String newVocabulary(Model model) {
        time = sdf.format(new Date());
        System.out.println(time + " SIKER Szótár készítés oldal betöltés");
        model.addAttribute("atadott_nev", login_name);
        return "make_vocabulary";
    }

    @GetMapping("/main")
    public String mainScreen(Model model){
        time = sdf.format(new Date());
        model.addAttribute("atadott_nev", login_name);
        model.addAttribute("given_list", user_vocabulary_list);
        System.out.println(time + " Visszalépés történt");
        return "main_screen";
    }

    //Ez még csak annyi, hogy listába teszi
    @PostMapping("/new")
    public String insertAWord(@ModelAttribute Words word_modell, Model model) {
        time = sdf.format(new Date());

        word_list = word_service.fillIn(word_modell.getAngol(), word_modell.getMagyar());
        model.addAttribute("atadott_nev", login_name);

        if(word_list != null) {
            model.addAttribute("szavak_lista_atadva", word_list);
            return "make_vocabulary";
        } else {
            System.out.println(time + "  Új szó feltöltése sikertelen!");
            return "error_page";
        }
    }

    @PostMapping("/save")
    public String saveVocabulary(@ModelAttribute Words word_modell, @ModelAttribute Vocabularies szotar, Model model) {
        time = sdf.format(new Date());

        //Adatok megjelenítése
        model.addAttribute("atadott_nev", login_name);
        model.addAttribute("given_list", user_vocabulary_list);

        //1 Szótár feltöltése - Még szavak nélkül
        Vocabularies uj_szotar = voc_serv.insertSzotar(szotar.getMegnevezes(), actual_user_id, 0);
        if(uj_szotar == null) return "error_page";
        //2 Szavak feltöltése - Ezzel együtt a kapcsolatok is
        if(!word_service.saveWords(actual_user_id)) return "error_page";

        //TODO A szavak számát is bele kell majd tenni!!
        return "main_screen";
    }

}


















