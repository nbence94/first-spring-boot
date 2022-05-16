package com.example.firstmyown.repository;

import com.example.firstmyown.model.Vocabularies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VocabularyRepository extends JpaRepository<Vocabularies, Integer> {
    int findTopByOrderByIdDesc();
    int findTopIdByUserid(int id);
}
