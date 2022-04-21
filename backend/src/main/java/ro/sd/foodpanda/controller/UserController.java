package ro.sd.foodpanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sd.foodpanda.model.User;
import ro.sd.foodpanda.service.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/adduser")
    public User newUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/users")
    public List<User> allUsers() {
        return userService.findAll();
    }
}
