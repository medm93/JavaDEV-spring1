package com.pgs.java_dev.controllers;

import com.pgs.java_dev.model.User;
import com.pgs.java_dev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/nickNames/")
    public String findUserNickName(@RequestParam("email") String email) {
        return userService.findUserNickNameByEmail(email);
    }

    @GetMapping("/emails/")
    public String findUserEmail(@RequestParam String nickName) {
        return userService.findUserEmailByNickName(nickName);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
