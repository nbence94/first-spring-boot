package com.example.firstmyown.service;

import com.example.firstmyown.model.Vocabularies;
import com.example.firstmyown.repository.VocabularyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VocabularyService {

    private final VocabularyRepository voc_repo;

    public VocabularyService(VocabularyRepository repo) {
        this.voc_repo = repo;
    }


    public List<Vocabularies> megjelenites(int id) {
        return voc_repo.findAll().stream().filter(szotar -> szotar.getUserid() == id).collect(Collectors.toList());
    }

    public Vocabularies insertSzotar(String megnevezes, int userid, int szavak_szama) {
        if(megnevezes.equals("")) return null;

        Vocabularies uj = new Vocabularies();

        uj.setMegnevezes(megnevezes);
        uj.setUserid(userid);
        uj.setJatszva(0);
        uj.setSzavak(szavak_szama);

        return voc_repo.save(uj);
    }

    public int lastVocabularyId(int userid) {
        //voc_repo.findAll().stream().filter(szo -> szo.getId() == userid).collect(Collectors.toList()).get(0).getUserid();
        return voc_repo.findAll().stream().filter(szo -> szo.getId() == userid).collect(Collectors.toList()).get(0).getUserid();
    }

}
