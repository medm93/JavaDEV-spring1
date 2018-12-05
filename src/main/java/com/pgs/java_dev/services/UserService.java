package com.pgs.java_dev.services;

import com.pgs.java_dev.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public List<User> getAllUsers(@RequestParam String orderBy) {
        if ("nickName".equals(orderBy)) {
            users.sort(Comparator.comparing(User::getNickName));
        } else if ("email".equals(orderBy)) {
            users.sort(Comparator.comparing(User::getEmail));
        }
        return users;
    }

    public ResponseEntity<String> findUserNickNameByEmail(String email) {
        Optional<User> searchResult = users.stream().filter(x -> x.getEmail().equals(email)).findAny();

        if (searchResult.isPresent()) {
            return ResponseEntity.ok(searchResult.get().getNickName());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> findUserEmailByNickName(String nickName) {
        Optional<User> searchResult = users.stream().filter(x -> x.getNickName().equals(nickName)).findAny();

        if (searchResult.isPresent()) {
            return ResponseEntity.ok(searchResult.get().getEmail());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<User> createUser(User user) {
        if (users.contains(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        int id = new Random().nextInt(100);
        user.setId(id);
        users.add(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).body(user);
    }

    public ResponseEntity<User> getUserById(int id) {
        Optional<User> searchResult = users.stream().filter(x -> x.getId() == id).findAny();
        return searchResult
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<User> updateUserById(int id, User updatedUser) {
        Optional<User> searchResult = users.stream().filter(x -> x.getId() == id).findAny();
        if (searchResult.isPresent()) {
            User user = searchResult.get();
            user.setNickName(updatedUser.getNickName());
            user.setEmail(updatedUser.getEmail());
            users.set(users.indexOf(user), user);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<User> deleteUserById(int id) {
        Optional<User> searchResult = users.stream().filter(x -> x.getId() == id).findAny();
        if (searchResult.isPresent()) {
            users.remove(searchResult.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
