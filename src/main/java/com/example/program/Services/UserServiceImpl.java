package com.example.program.Services;


import com.example.program.dto.UserDto;
import com.example.program.models.Role;
import com.example.program.models.User;
import com.example.program.repository.RoleRepository;
import com.example.program.repository.UserRepository;
import com.example.program.Services.UserService;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setExpiredate(userDto.getExpiredate());
        user.setRole_name(userDto.getRole_name());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email)  throws UsernameNotFoundException {

        User user = new User();

        System.out.println("--------UserRepostory......findByEMail................");
        if (user.isExpired()) {
            throw new LockedException("Account is expired");
        }

        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {

        System.out.println("--------CustomUserDetailsService......findAllUsers.........");

        List<User> users = userRepository.findAll();
        /*
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
         */
        LocalDate currentDate = LocalDate.now();
        System.out.println("Date :"+currentDate);
        return users.stream()
                .filter(user -> user.getExpiredate() != null && user.getExpiredate().isAfter(currentDate) || user.getExpiredate().isEqual(currentDate))
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
