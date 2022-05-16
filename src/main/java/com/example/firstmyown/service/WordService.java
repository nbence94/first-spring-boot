package com.example.firstmyown.service;

import com.example.firstmyown.model.Words;
import com.example.firstmyown.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class WordService {

    @Autowired
    private final WordRepository word_repository;

    private ConnectionService conn_service;
    private final VocabularyService vocabulary_service;

    List<Words> tmp_list;
    int tmp_id = 0;

    public WordService(WordRepository repository, ConnectionService connection_service, VocabularyService vocabularyService) {
        this.word_repository = repository;
        tmp_list = new ArrayList<>();
        this.conn_service = connection_service;
        this.vocabulary_service = vocabularyService;
    }

    public List<Words> megjelenit(int szo_azonosito) {
        return word_repository.findAll().stream().filter(szo -> szo.getId() == szo_azonosito).collect(Collectors.toList());
    }

    public boolean saveWords(int userid) {
        if(tmp_list.size() == 0) return false;
        for (Words words : tmp_list) {
            System.out.println("DEBUG2: " + words.getAngol() + " // "  + words.getMagyar());
            words.setAngol(words.getAngol());
            words.setMagyar(words.getMagyar());
            word_repository.save(words);

            System.out.println("DEBUG-3: " + vocabulary_service.lastVocabularyId(userid));
            System.out.println("DEBUG-4: " + getLastWordId(words.getAngol()));

            conn_service.insertKapcsolat(vocabulary_service.lastVocabularyId(userid), getLastWordId(words.getAngol()));
        }

        return true;
    }

    public int getNumbersOfWords() {return tmp_list.size(); }


    //Csak simán listába tenni és megjeleníteni. Ezeknek az elemei kellenek majd a
    public List<Words> fillIn(String angol, String magyar) {
        if(!angol.equals("") && !magyar.equals("")) {
            Words uj = new Words();

            uj.setId(tmp_id++);
            uj.setAngol(angol.trim().toLowerCase(Locale.ROOT));
            uj.setMagyar(magyar.trim().toLowerCase(Locale.ROOT));
            tmp_list.add(uj);
            System.out.println("DEBUG: (" + tmp_id + ") " +  tmp_list.get(tmp_list.lastIndexOf(uj)).getAngol()+ " || " + tmp_list.get(tmp_list.lastIndexOf(uj)).getMagyar());

            return tmp_list;
        } else {
            return null;
        }
    }

    public int getLastWordId(String angol) {
        return word_repository.findAll().stream().filter(szo -> szo.getAngol().equals(angol)).collect(Collectors.toList()).get(0).getId();
    }

}
