package com.example.firstmyown.repository;

import com.example.firstmyown.model.Vocabularies;
import com.example.firstmyown.model.Words;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordRepository extends JpaRepository<Words, Integer> {
    int findTopByOrderByIdDesc();
}
