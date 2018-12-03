package com.pgs.java_dev.services;

import com.pgs.java_dev.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    public String findUserNickNameByEmail(String email) {
        List<User> users = getAllUsers();

        Optional<User> searchResult =  users.stream().filter(x -> x.getEmail().equals(email)).findAny();

        String message = "No user with given email.";
        if (searchResult.isPresent()) {
            message = searchResult.get().getNickName();
        }

        return message;
    }


    public List<User> getAllUsers() {
        User user1 = new User(1, "HansKloss", "hans@kloss.com");
        User user2 = new User(2, "JamesBond", "james@bond.com");

        return new ArrayList<>(Arrays.asList(user1,user2));
    }

    public User getUserById(int id) {
        List<User> users = getAllUsers();

        Optional<User> searchResult =  users.stream().filter(x -> x.getId() == id).findAny();

        return searchResult.get();
    }

    public User createUser(User user) {
        Random random = new Random();
        user.setId(random.nextInt(100));
        return user;
    }
}
