package com.example.firstmyown.repository;

import com.example.firstmyown.model.Words;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Words, Integer> {

}
