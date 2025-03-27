package com.cap_talend.program.controllers;


import com.cap_talend.program.Services.UserService;
import com.cap_talend.program.dto.UserDto;
import com.cap_talend.program.models.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

import com.cap_talend.program.dto.UserPwdDto;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    private UserService userService;

    //private UserPwdDto userpwddto;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/")
    public String init_loginForm() {
        UserDto user = new UserDto();
        return "login";
    }


    @GetMapping("login")
    public String loginForm() {
        UserDto user = new UserDto();
         return "login";
    }

    @PostMapping("/login")
    public String loginForm1(@RequestBody UserPwdDto userPwdDto) {
        return "login";
    }




    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    //@RequestBody @Valid
    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){

        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        System.out.println("-------AuthController :showRegistrationForm --------------------------------------");

        if (user.getExpiredate() != null || user.getExpiredate().isBefore(LocalDate.now())) {
            result.rejectValue("expiredate", null, "Expire Date should not be null!");
        }


        user.setExpiredate(user.getExpiredate());

       // LocalDate ld = user.getExpiredate();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //user.setExpiredate(LocalDate.parse(ld, formatter));

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }


        userService.saveUser(user);

        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        // .getExpiredate().isBefore(LocalDate.now()))
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}