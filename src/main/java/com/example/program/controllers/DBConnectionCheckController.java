package com.example.program.controllers;
// DBConnectionCheckController

// DBConnectionCheckRepository


import com.example.program.Services.*;

import com.example.program.models.MetaDataModel;
import com.example.program.Services.MetaDataService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="/Main", method=RequestMethod.POST)
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

//        ModelAndView modelAndView = new ModelAndView("getAll");
//        modelAndView.addObject("connectionRequest", new DBConnectionRequest());
          model.addAttribute("connectionRequest", new DBConnectionRequest());

        return "redirect:/Metadatamodels";
        //return ModelAndView;
    }





    @GetMapping("/databaseconnectioncheck")
    public ModelAndView databaseconnectioncheck1() {
        //ModelAndView modelAndView = new ModelAndView("DatabaseConnectionCheck");
        ModelAndView modelAndView = new ModelAndView("test_connection");
        modelAndView.addObject("connectionRequest", new DBConnectionRequest());
        return modelAndView;
    }

    @GetMapping("/databaseSelection")
    public ModelAndView databaseSelection_fun() {
        ModelAndView modelAndView = new ModelAndView("DatabaseConnectionCheck");
        modelAndView.addObject("connectionRequest", new DBConnectionRequest());
        return modelAndView;
    }

    @GetMapping("/navigation")
    public ModelAndView navigation() {
        ModelAndView modelAndView = new ModelAndView("Navigation_ProfilingCustom");
        modelAndView.addObject("connectionRequest", new DBConnectionRequest());

        return modelAndView;
    }


    @PostMapping("/test_connection")
    public ModelAndView testConnection(@ModelAttribute DBConnectionRequest connectionRequest,
                                       Model model, HttpSession session) {

        try {
            boolean isConnected = dBconnectionService.testConnection(connectionRequest);
            if (isConnected) {
                System.out.println("It connected...");

                session.setAttribute("S_DB_Name",connectionRequest.getDatabase());
                session.setAttribute("S_DB_HostName",connectionRequest.getHostname());

                //return new ModelAndView("redirect:/metadata/getAll");
                return new ModelAndView("redirect:/Main/navigation");
            } else {
                System.out.println("It Fail to connected...");
                model.addAttribute("error", "Connection Is Wrong, check the fields");
                //return new ModelAndView("redirect:/databaseSelection");
                return new ModelAndView("redirect:/Main/databaseSelection?loginError=true");
            }
        } catch (Exception e) {
            System.out.println("It Error to connected...");
            log.error(e.getMessage());
            return new ModelAndView("redirect:/databaseSelection");
        }

    }

}