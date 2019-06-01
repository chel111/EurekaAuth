package com.chel.auth.controllers;
import com.chel.auth.entities.User;
import com.chel.auth.models.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @RequestMapping("/")
    public String home() {
        return "Hello from Auth Service running at port: " + env.getProperty("local.server.port");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkUser(@RequestBody String body) {
        User user = new Gson().fromJson(body, User.class);
        return userService.authenticate(user);
    }
}