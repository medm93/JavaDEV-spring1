package com.pgs.java_dev.services;

import com.pgs.java_dev.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public String findUserNickNameByEmail(String email) {
        Optional<User> searchResult = users.stream().filter(x -> x.getEmail().equals(email)).findAny();

        String message = "No user with given email.";
        if (searchResult.isPresent()) {
            message = searchResult.get().getNickName();
        }

        return message;
    }

    public String findUserEmailByNickName(String nickName) {
        Optional<User> searchResult = users.stream().filter(x -> x.getNickName().equals(nickName)).findAny();

        if (searchResult.isPresent()) {
            return searchResult.get().getEmail();
        } else {
            return "No user with given nickName.";
        }
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User createUser(User user) {
        Random random = new Random();
        user.setId(random.nextInt(100));
        users.add(user);
        return user;
    }

    public User getUserById(int id) {
        Optional<User> searchResult = users.stream().filter(x -> x.getId() == id).findAny();

        return searchResult.get();
    }

    public String updateUserById(int id, User updatedUser) {
        Optional<User> searchResult = users.stream().filter(x -> x.getId() == id).findAny();
        if (searchResult.isPresent()) {
            User user = searchResult.get();
            user.setNickName(updatedUser.getNickName());
            user.setEmail(updatedUser.getEmail());
            users.set(users.indexOf(user), user);
            return "User is update";
        } else {
            return "User is not found";
        }
    }

    public String deleteUserById(int id) {
        Optional<User> searchResult = users.stream().filter(x -> x.getId() == id).findAny();
        if (searchResult.isPresent()) {
            users.remove(searchResult.get());
            return "User is removed";
        } else {
            return "User is not found";
        }

    }
}
