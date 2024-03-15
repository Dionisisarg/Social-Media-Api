package com.example.SocialMediaApi.Controller;

import com.example.SocialMediaApi.Dto.LoginDTO;
import com.example.SocialMediaApi.Dto.UserDTO;
import com.example.SocialMediaApi.Service.UserService;
import com.example.SocialMediaApi.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {

   @Autowired
    private  UserService userService;


    @PostMapping(path = "/save")
    public String saveUser(@RequestBody UserDTO userDTO){
        String id = userService.addUser(userDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }


}
