package com.example.firstmyown.service;

import com.example.firstmyown.model.Users;
import com.example.firstmyown.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository repo;

    public UserService(UserRepository repository) {
        this.repo = repository;
    }

    public Users insertUser(String neve, String jelszava, String emailje) {
        if(!neve.equals("") && !jelszava.equals("")) {
            if(repo.findFirstByNev(neve).isPresent()) {
                return null;
            }

            Users user = new Users();
            user.setNev(neve);
            user.setJelszo(jelszava);
            user.setEmail(emailje);

            return repo.save(user);
        } else {
            return null;
        }
    }

    public Users selectUser(String nev, String jelszo) {
        return repo.findUserByNevAndJelszo(nev, jelszo).orElse(null);
    }



}
