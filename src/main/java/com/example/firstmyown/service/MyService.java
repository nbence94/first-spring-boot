package com.example.firstmyown.service;

import com.example.firstmyown.model.Users;
import com.example.firstmyown.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final UserRepository repo;

    public MyService(UserRepository repository) {
        this.repo = repository;
    }

    public Users insertUser(String neve, String jelszava, String emailje) {
        if(!neve.equals("") && !jelszava.equals("")) {
            if(repo.findFirstByNev(neve).isPresent()) {
                System.out.println("Belemegy");
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

    public Users selectUser(String neve, String jelszava) {
            return repo.findUserByNevAndJelszo(neve, jelszava).orElse(null);
    }

}
