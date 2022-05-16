package com.example.firstmyown.repository;

import com.example.firstmyown.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findFirstByNev(String name);
    Optional<Users> findByNevAndJelszo(String name, String password);
}
