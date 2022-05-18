package com.example.firstmyown.service;

import com.example.firstmyown.model.Vocabularies;
import com.example.firstmyown.repository.VocabularyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VocabularyService {

    private final VocabularyRepository repo;

    public VocabularyService(VocabularyRepository vocabulary_repository) {
        this.repo = vocabulary_repository;
    }


    public List<Vocabularies> megjelenites(int id) {
        return repo.findAll().stream()
                .filter(szotar -> szotar.getFelhasznaloid() == id)
                .collect(Collectors.toList());
    }

    public Vocabularies insertSzotar(String megnevezes, int userid, int szavak_szama) {
        if(megnevezes.equals("")) return null;

        Vocabularies uj = new Vocabularies();

        uj.setMegnevezes(megnevezes);
        uj.setFelhasznaloid(userid);
        uj.setJatszva(0);
        uj.setSzavak(szavak_szama);

        return repo.save(uj);
    }

    public int lastVocabularyId(int userid) {
        List<Vocabularies> tmp_list = repo.findAll()
                .stream()
                .filter(szo -> szo.getFelhasznaloid() == userid)
                .collect(Collectors.toList());



        return repo.findAll()
                .stream()
                .filter(szo -> szo.getFelhasznaloid() == userid)
                .collect(Collectors.toList())
                .get(tmp_list.size()-1).getId();
    }

}
