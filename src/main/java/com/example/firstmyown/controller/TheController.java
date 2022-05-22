package com.example.firstmyown.controller;

import com.example.firstmyown.model.Connections;
import com.example.firstmyown.model.Users;
import com.example.firstmyown.model.Vocabularies;
import com.example.firstmyown.model.Words;
import com.example.firstmyown.repository.ConnectionRepository;
import com.example.firstmyown.service.ConnectionService;
import com.example.firstmyown.service.UserService;
import com.example.firstmyown.service.VocabularyService;
import com.example.firstmyown.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private final VocabularyService vocabulary_service;

    private final WordService word_service;

    private final ConnectionService connection_service;
    public TheController(UserService service, VocabularyService vocabulary_service, WordService wservice, ConnectionService connection_service) {
        this.user_service = service;
        this.vocabulary_service = vocabulary_service;
        this.word_service = wservice;
        this.connection_service = connection_service;
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

    @RequestMapping(value="/main", params="belepes",method=RequestMethod.POST)
    public String login(@ModelAttribute Users user_modell, Model model) {
        time = sdf.format(new Date());

        Users belepett = user_service.selectUser(user_modell.getNev(), user_modell.getJelszo());
        if (belepett != null) {
            login_name = belepett.getNev();
            actual_user_id = belepett.getId();
            model.addAttribute("atadott_nev", login_name);

            user_vocabulary_list = vocabulary_service.megjelenites(actual_user_id);
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

        model.addAttribute("szotar", new Vocabularies());
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

    @PostMapping("/new")
    public String putWordInList(@ModelAttribute Words word, Model model) {
        time = sdf.format(new Date());
        model.addAttribute("atadott_nev", login_name);

        word_list = word_service.putWordsInTmpList(word.getAngol(), word.getMagyar());
        if(word_list != null) {
            model.addAttribute("szavak_lista_atadva", word_list);
            System.out.println(time + "  Új szó feltöltése sikeres!");
            return "make_vocabulary";
        } else {
            System.out.println(time + "  Új szó feltöltése SIKERTELEN!");
            return "error_page";
        }
    }

    @RequestMapping(value="/main", params="szotar", method=RequestMethod.POST)
    public String saveVocabulary(@ModelAttribute("szotar") Vocabularies szotar, Model model) {
        time = sdf.format(new Date());

        Vocabularies uj_szotar = vocabulary_service.insertSzotar(szotar.getMegnevezes(), word_list.size(), actual_user_id);
        if (uj_szotar == null) return "error_page";

        if (!word_service.saveWords(actual_user_id, word_list)) return "error_page";

        model.addAttribute("atadott_nev", login_name);
        user_vocabulary_list = vocabulary_service.megjelenites(actual_user_id);
        model.addAttribute("given_list", user_vocabulary_list);

        System.out.println(time + " Szótár létrehozva!");
        return "main_screen";
    }

    @RequestMapping("/delete/{id}")
    public String deleteVocabulary(@PathVariable(name = "id") int id) {
        time = sdf.format(new Date());

        List<Connections> conns = connection_service.getConnections(id);
        for (Connections connections : conns) {
            int wordid = connections.getId().getWordid();
            connection_service.deleteByWord(wordid);
            word_service.deleteWord(wordid);
        }
        vocabulary_service.delete(id);

        System.out.println(time + " A szótár (azonosító: " + id + ") törölve");
        user_vocabulary_list = vocabulary_service.megjelenites(actual_user_id);

        return "redirect:/main";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editVocabulary(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("make_vocabulary");
        //Kell a szótár
        Vocabularies vocabulary = vocabulary_service.selectVocabularyById(id);

        //Kellenek hozzá a szavak
        List<Connections> conns = connection_service.getConnections(id);
        word_list = new ArrayList<>();
        for (Connections connections : conns) {
            int wordid = connections.getId().getWordid();
            word_list.add(word_service.selectWordById(wordid));
        }

        mav.addObject("szotar", vocabulary);
        mav.addObject("szavak_lista_atadva", word_list);
        mav.addObject("atadott_nev", login_name);

        time = sdf.format(new Date());
        System.out.println(time + " " + vocabulary.getMegnevezes() + " szótár megnyitva módosításra!");

        return mav;
    }

}


















