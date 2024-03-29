package com.example.firstmyown.repository;

import com.example.firstmyown.model.ConnectionPrimaries;
import com.example.firstmyown.model.Connections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connections, ConnectionPrimaries> {
    void deleteByIdWordid(int word);
    List<Connections> findByIdVocabularyid(int vocabularyid);
}

