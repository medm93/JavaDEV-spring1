package com.pgs.java_dev.controllers;

import com.pgs.java_dev.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "All users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id) {
        return String.format("Get user with id %d", id);
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        return String.format("Created user with details: %s", user.toString());
    }
}
