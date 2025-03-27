package com.cap_talend.program.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping(value = "/DBCTalendProject/index", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String getMain() {
        return "Welcome";
    }
}