package com.example.program.Services;

import com.example.program.dto.UserDto;
import com.example.program.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
