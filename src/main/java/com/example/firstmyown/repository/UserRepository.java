package com.example.firstmyown.repository;

import com.example.firstmyown.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    /*Optional<Users> findUser(String login, String password);*/
    //Optional<Users> findUser(String find_this);
}