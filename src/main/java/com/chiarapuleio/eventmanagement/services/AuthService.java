package com.chiarapuleio.eventmanagement.services;

import com.chiarapuleio.eventmanagement.dao.UserDAO;
import com.chiarapuleio.eventmanagement.entities.User;
import com.chiarapuleio.eventmanagement.exceptions.BadRequestException;
import com.chiarapuleio.eventmanagement.exceptions.UnauthorizedException;
import com.chiarapuleio.eventmanagement.payloads.UserDTO;
import com.chiarapuleio.eventmanagement.payloads.loginPayloads.UserLoginDTO;
import com.chiarapuleio.eventmanagement.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userSrv;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUserAndGenerateToken(UserLoginDTO userLog){
        User user = userSrv.findByEmail(userLog.email());
        if(bcrypt.matches(userLog.password(), user.getPassword())){
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Invalid credentials, try again.");
        }
    }

    public User save(UserDTO userLog){
        userDAO.findByEmail(userLog.email()).ifPresent(user -> {
            throw new BadRequestException(user.getEmail() + " is already in use.");
        });
        User newUser = new User(userLog.name(), userLog.surname(), userLog.email(), bcrypt.encode(userLog.password()));
        return userDAO.save(newUser);
    }
}
