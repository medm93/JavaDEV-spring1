package com.pgs.java_dev.controllers;

import com.pgs.java_dev.model.User;
import com.pgs.java_dev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false, defaultValue="nickName") String orderBy) {
        return userService.getAllUsers(orderBy);
    }

    @GetMapping("/nickNames/")
    public ResponseEntity<String> findUserNickName(@RequestParam("email") String email) {
        return userService.findUserNickNameByEmail(email);
    }

    @GetMapping("/emails/")
    public ResponseEntity<String> findUserEmail(@RequestParam String nickName) {
        return userService.findUserEmailByNickName(nickName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        return userService.deleteUserById(id);
    }
}
