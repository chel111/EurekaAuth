package com.chel.auth.models;


import com.chel.auth.entities.User;
import com.chel.auth.repos.UserRepo;
import com.chel.auth.security.JwtConfig;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public String authenticate(User user) {
        Optional<User> userAuth = userRepo.findByLogin(user.getLogin());
        if(!userAuth.get().getPass().equals(user.getPass())) return null;
        return JwtConfig.createJWT(new Gson().toJson(userAuth));
    }

}
