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

    private final ConnectionService conn_service;
    private final VocabularyService vocabulary_service;

    List<Words> tmp_list;
    int tmp_id = 1;

    public WordService(WordRepository repository, ConnectionService connection_service, VocabularyService vocabularyService) {
        this.word_repository = repository;
        this.conn_service = connection_service;
        this.vocabulary_service = vocabularyService;
        tmp_list = new ArrayList<>();
    }

    public boolean saveWords(int userid) {

        for (Words words : tmp_list) {
            Words uj_szo = new Words();
            uj_szo.setAngol(words.getAngol());
            uj_szo.setMagyar(words.getMagyar());
            word_repository.save(uj_szo);

            conn_service.insertKapcsolat(vocabulary_service.lastVocabularyId(userid), getLastWordId(words.getAngol()));
        }

        tmp_id = 1;
        return true;
    }

    //Csak simán listába tenni és megjeleníteni. Ezeknek az elemei kellenek majd a
    public List<Words> putWordsInTmpList(String angol, String magyar) {
        if(!angol.equals("") && !magyar.equals("")) {
            Words uj = new Words();

            uj.setId(tmp_id++);
            uj.setAngol(angol.trim().toLowerCase(Locale.ROOT));
            uj.setMagyar(magyar.trim().toLowerCase(Locale.ROOT));
            tmp_list.add(uj);

            return tmp_list;
        } else {
            return null;
        }
    }

    public int getLastWordId(String angol) {
        List<Words> tmp_list = word_repository.findAll()
                .stream()
                .filter(szo -> szo.getAngol().equals(angol))
                .collect(Collectors.toList());

        return word_repository.findAll()
                .stream()
                .filter(szo -> szo.getAngol().equals(angol))
                .collect(Collectors.toList())
                .get(tmp_list.size() - 1).getId();
    }

}
