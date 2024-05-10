package com.example.program.controllers;
// DBConnectionCheckController

// DBConnectionCheckRepository


import com.example.program.Services.*;

import com.example.program.models.MetaDataModel;
import com.example.program.Services.MetaDataService;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Main")
@Log4j2
public class DBConnectionCheckController {

    @Autowired
    DBconnectionService dBconnectionService;

    @Autowired(required=true)
    private MetaDataService metadataService;

    @GetMapping("/Home")
    public String databaseSelection() {
        return "Home";
    }

    @GetMapping("/getAll")
    public String getAll(Model model) {
        List<MetaDataModel> stlist = metadataService.getAll();
        model.addAttribute("metadatamodels", stlist);
        //return "metadatamodels";
        return "Metadatamodels";
    }

    @GetMapping("/databaseconnectioncheck")
    public ModelAndView databaseSelection1() {
        ModelAndView modelAndView = new ModelAndView("DatabaseConnectionCheck");
        modelAndView.addObject("connectionRequest", new DBConnectionRequest());
        return modelAndView;
    }

    @GetMapping("/databaseSelection")
    public ModelAndView databaseSelection2() {
        ModelAndView modelAndView = new ModelAndView("DatabaseConnectionCheck");
        modelAndView.addObject("connectionRequest", new DBConnectionRequest());
        return modelAndView;
    }


    @PostMapping("/test-connection")
    public ModelAndView testConnection(@ModelAttribute DBConnectionRequest connectionRequest,
                                       Model model) {


        try {
            boolean isConnected = dBconnectionService.testConnection(connectionRequest);
            if (isConnected) {
                System.out.println("It connected...");
                //return new ModelAndView("redirect:/profiling");
                return new ModelAndView("redirect:/metadatamodels/getAll");
            } else {
                System.out.println("It Fail to connected...");
                model.addAttribute("error", "Connection Is Wrong, check the fields");
                //return new ModelAndView("redirect:/databaseSelection");
                return new ModelAndView("redirect:/databaseSelection?loginError=true");
            }
        } catch (Exception e) {
            System.out.println("It Error to connected...");
            log.error(e.getMessage());
            return new ModelAndView("redirect:/databaseSelection");
        }

    }

}