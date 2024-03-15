package com.example.SocialMediaApi.Service;

import com.example.SocialMediaApi.Dto.LoginDTO;
import com.example.SocialMediaApi.Dto.UserDTO;
import com.example.SocialMediaApi.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String addUser(UserDTO userDTO);
    LoginResponse loginUser(LoginDTO loginDTO);


}
