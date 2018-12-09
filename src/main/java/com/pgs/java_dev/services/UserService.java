package com.pgs.java_dev.services;

import com.pgs.java_dev.model.User;
import com.pgs.java_dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    @Autowired
    private UserRepository repository;

    public String findUserNickNameByEmail(String email) {
        Optional<User> searchResult =  users.stream().filter(x -> x.getEmail().equals(email)).findAny();

        String message = "No user with given email.";
        if (searchResult.isPresent()) {
            message = searchResult.get().getNickName();
        }

        return message;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        Optional<User> searchResult =  users.stream().filter(x -> x.getId() == id).findAny();

        return searchResult.get();
    }

    public User createUser(User user) {
        return  repository.save(user);
    }
}
