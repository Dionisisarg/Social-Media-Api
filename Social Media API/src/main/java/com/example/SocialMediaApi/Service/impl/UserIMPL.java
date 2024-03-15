package com.example.SocialMediaApi.Service.impl;

import com.example.SocialMediaApi.Dto.LoginDTO;
import com.example.SocialMediaApi.Dto.UserDTO;
import com.example.SocialMediaApi.Entity.User;
import com.example.SocialMediaApi.Repository.UserRepo;
import com.example.SocialMediaApi.Service.UserService;
import com.example.SocialMediaApi.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getType()
        );

        userRepo.save(user);
        return user.getEmail();
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO){
        String msg = "";
        User user1 = userRepo.findByEmail(loginDTO.getEmail());
        if(user1 != null){
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight){
                Optional<User> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()){
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("The password is wrong", false);
            }
        } else {
            return new LoginResponse("Email is wrong", false);
        }
    }
}
