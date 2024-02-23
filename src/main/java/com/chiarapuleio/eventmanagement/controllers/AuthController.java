package com.chiarapuleio.eventmanagement.controllers;

import com.chiarapuleio.eventmanagement.entities.User;
import com.chiarapuleio.eventmanagement.payloads.UserDTO;
import com.chiarapuleio.eventmanagement.payloads.loginPayloads.UserLogRespDTO;
import com.chiarapuleio.eventmanagement.payloads.loginPayloads.UserLoginDTO;
import com.chiarapuleio.eventmanagement.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authSrv;

    @PostMapping("/login")
    public UserLogRespDTO login(@RequestBody UserLoginDTO userLog){
        String accessToken = authSrv.authenticateUserAndGenerateToken(userLog);
        return new UserLogRespDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserDTO newUser){
        return this.authSrv.save(newUser);
    }
}
