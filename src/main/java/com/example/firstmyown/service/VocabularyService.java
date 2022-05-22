package com.example.firstmyown.service;

import com.example.firstmyown.model.Vocabularies;
import com.example.firstmyown.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Vocabularies insertSzotar(String megnevezes, int szavak_szama, int userid) {
        if(megnevezes.equals("")) return null;

        Vocabularies uj = new Vocabularies();
        uj.setMegnevezes(megnevezes);
        uj.setSzavak(szavak_szama);
        uj.setJatszva(0);
        uj.setFelhasznaloid(userid);

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

    @Transactional
    public void delete(int id) {
        repo.deleteById(id);
    }

    public Vocabularies selectVocabularyById(int id) {
        return repo.findById(id);
    }

}















