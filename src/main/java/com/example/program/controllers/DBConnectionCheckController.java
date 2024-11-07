package com.example.program.controllers;
// DBConnectionCheckController

// DBConnectionCheckRepository


import com.example.program.Services.*;

import com.example.program.Services.DBConnectionRequest;
import com.example.program.dto.DBNameDTO;
import com.example.program.models.DQ_RulesModel;
import com.example.program.models.MetaDataModel;
import com.example.program.Services.MetaDataService;


import com.example.program.models.DBConnectionCheckModel;
import com.example.program.Services.DBConnectionCheckService;

import com.example.program.models.MetaDataModel;
import com.example.program.Services.DBConnectionCheckService;


import com.example.program.repository.DBConnectionCheckRepository;
import com.example.program.repository.DBNameDTORepository;
import com.example.program.repository.SchemaRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private DBConnectionCheckService connection_check_service;

    @Autowired
    private DBconnectionService dbconnectionService;

    private DBNameDTORepository dbNameDTORepository;
    private DBConnectionCheckRepository dbConnectionCheckRepository;

    @Autowired
    DBConnectionCheckService serv;


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

    // Model model, @RequestParam String trans, @ModelAttribute DBConnectionRequest connectionRequest, HttpSession session

    //public ModelAndView databaseSelection_fun(Model model) {
    @GetMapping("/databaseSelection")
    public ModelAndView databaseSelection_fun(Model model, @ModelAttribute DBConnectionRequest connectionRequest, HttpSession session) {
        //ModelAndView modelAndView = new ModelAndView("DatabaseConnectionCheck");

        System.out.println("Hi Ram you are at -- DBConnectionCheckController --> databaseSelection_fun -- Step-1 ");

        ModelAndView modelAndView = new ModelAndView("Main/DatabaseConnectionCheck");
        modelAndView.addObject("connectionRequest", new DBConnectionRequest());

        //--------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------


        List<String> dbnames =  new ArrayList<>();
        //dbConnectionCheckRepository.getDB_Name().forEach(e-> dbnames.add(e.getId().toString()));

        //dbConnectionCheckRepository.getDB_Name().forEach(e-> dbnames.add(e.getDb_connection_name()));
        //dbnames.add("Ram");

        serv.getAllDbNames().forEach(e-> dbnames.add(e.getDb_name()));
        model.addAttribute("dbnames", dbnames);

        //--------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------

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

                session.setAttribute("S_DBConnection_Name","postgres");
                session.setAttribute("S_Name",connectionRequest.getName());
                session.setAttribute("S_DB_Name",connectionRequest.getDatabase());
                session.setAttribute("S_DB_Port",connectionRequest.getPort());
                session.setAttribute("S_DB_HostName",connectionRequest.getHostname());
                session.setAttribute("S_DB_User",connectionRequest.getUsername());


                DBConnectionCheckModel dc =  new DBConnectionCheckModel();

                dc.setDb_connection_name("postgres");
                dc.setDb_name(connectionRequest.getName());
                dc.setDb_database(connectionRequest.getDatabase());
                dc.setDb_hostname(connectionRequest.getHostname());
                dc.setDb_port(connectionRequest.getPort());
                dc.setDb_username(connectionRequest.getUsername());
                dc.setDb_password(connectionRequest.getPassword());

                DBConnectionCheckModel dc1 = connection_check_service.save(dc);

                //dBconnectionService

/*
                DBConnectionCheckModel m = new DBConnectionCheckModel();
                DBConnectionCheckService s = new DBConnectionCheckService();


                m.setDbsource(connectionRequest.getDatabase());
                m.setHostname(connectionRequest.getHostname());
                m.setPort(connectionRequest.getPort());
                m.setUsername(connectionRequest.getUsername());
                m.setPassword(connectionRequest.getPassword());
                m.setConnection_name(String.join(connectionRequest.getDatabase(),"-",connectionRequest.getHostname()));
                s.save(m);
                */
                return new ModelAndView("redirect:/Main/navigation");
            } else {
                System.out.println("It Fail to connected...");

                System.out.println("DB name - "+connectionRequest.getDbName());
                System.out.println("portname - "+connectionRequest.getPort());
                System.out.println("Host - "+connectionRequest.getHostname());
                System.out.println("username - "+connectionRequest.getUsername());

                model.addAttribute("error", "Connection Is Wrong, check the fields");
                //return new ModelAndView("redirect:/databaseSelection");
                return new ModelAndView("redirect:/Main/databaseSelection?loginError=true");
            }
        } catch (Exception e) {
            System.out.println("It Error to connected...");
            log.error(e.getMessage());
            model.addAttribute("error", e.getMessage());

            return new ModelAndView("redirect:/Main/databaseSelection");
        }

    }

}