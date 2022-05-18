package com.example.firstmyown.repository;

import com.example.firstmyown.model.Vocabularies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabularies, Integer> {
    int findTopIdByFelhasznaloid(int id);
}
