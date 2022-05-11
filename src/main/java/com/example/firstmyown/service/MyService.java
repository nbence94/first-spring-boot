package com.example.firstmyown.service;

import com.example.firstmyown.model.Users;
import com.example.firstmyown.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    private final UserRepository repo;

    public MyService(UserRepository repository) {
        this.repo = repository;
    }

    public Users insertUser(String neve, String jelszava, String emailje) {
        if(!neve.equals("") && !jelszava.equals("")) {
            /*if(repo.findUser(neve).isPresent() || repo.findUser(emailje).isPresent()) {
                return null;
            }*/

            Users user = new Users();
            user.setNev(neve);
            user.setJelszo(jelszava);
            user.setEmail(emailje);

            return repo.save(user);
        } else {
            return null;
        }
    }

}
