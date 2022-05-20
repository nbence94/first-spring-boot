package com.example.firstmyown.service;

import com.example.firstmyown.model.Words;
import com.example.firstmyown.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class WordService {

    List<Words> tmp_list; //Átmeneti lista, ami a szavakat tárolja
    int tmp_id = 1; //Átmeneti id számláló

    private final WordRepository word_repository;
    private final VocabularyService vocabulary_service;
    private final ConnectionService conn_service;
    public WordService(WordRepository repository,
                       VocabularyService vocabulary_service,
                       ConnectionService connection_service) {
        this.word_repository = repository;
        tmp_list = new ArrayList<>();
        this.vocabulary_service = vocabulary_service;
        this.conn_service = connection_service;
    }

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

    public boolean saveWords(int userid) {

        for (Words words : tmp_list) {
            //A listában lévő, aktuális szó feltöltése
            Words uj_szo = new Words();
            uj_szo.setAngol(words.getAngol());
            uj_szo.setMagyar(words.getMagyar());
            word_repository.save(uj_szo);

            //A szóval együtt a kapcsolat is feltöltésre kerül a Connection-be
            conn_service.insertKapcsolat(
                    vocabulary_service.lastVocabularyId(userid),
                    getLastWordId(words.getAngol()));
        }

        tmp_id = 1;
        return true;
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

    @Transactional
    public void deleteWord(int wordid) {
        word_repository.deleteById(wordid);
    }

}













