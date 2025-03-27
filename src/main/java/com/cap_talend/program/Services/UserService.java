package com.cap_talend.program.Services;

import com.cap_talend.program.dto.UserDto;
import com.cap_talend.program.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

}
