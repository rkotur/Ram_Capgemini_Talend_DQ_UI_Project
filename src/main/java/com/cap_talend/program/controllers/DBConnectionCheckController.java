package com.cap_talend.program.controllers;
// DBConnectionCheckController

// DBConnectionCheckRepository


import com.cap_talend.program.Services.*;
import com.cap_talend.program.Services.*;

import com.cap_talend.program.dto.DBNameDTO;
import com.cap_talend.program.models.MetaDataModel;


import com.cap_talend.program.models.DBConnectionCheckModel;

import com.cap_talend.program.Services.DBConnectionCheckService;


import com.cap_talend.program.repository.DBConnectionCheckRepository;
import com.cap_talend.program.repository.DBNameDTORepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
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


        List<DBNameDTO> dbnames =  new ArrayList<>();

        dbnames.add(new DBNameDTO(-1,"--Select Value--"));

        serv.getAllDbNames().forEach(e-> dbnames.add(new DBNameDTO(e.getId(),e.getDb_name())));
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

                session.setAttribute("S_Name", connectionRequest.getName());
                session.setAttribute("S_DBConnection_Name", connectionRequest.getDbsource());
                session.setAttribute("S_DB_Name", connectionRequest.getDbName());
                session.setAttribute("S_DB_Port", connectionRequest.getPort());
                session.setAttribute("S_DB_HostName", connectionRequest.getHostname());
                session.setAttribute("S_DB_User", connectionRequest.getUsername());

                DBConnectionCheckModel dc = new DBConnectionCheckModel();
                /*
                System.out.println("Name:"+connectionRequest.getName()+" - ID: "+connectionRequest.getId().toString());
                if (connectionRequest.getId() != -1 )
                {
                }
                */

                dc.setId(connectionRequest.getId());
                dc.setDb_name(connectionRequest.getName()); // Ram DB Connection
                dc.setDb_connection_name(connectionRequest.getDbsource()); //  Postgres / MySQL
                dc.setDb_database(connectionRequest.getDbName()); // Postgres
                dc.setDb_hostname(connectionRequest.getHostname()); // localhost
                dc.setDb_port(connectionRequest.getPort()); // 5432
                dc.setDb_username(connectionRequest.getUsername()); // postgres
                dc.setDb_password(connectionRequest.getPassword());

                DBConnectionCheckModel dc1 = connection_check_service.save(dc);

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

    @GetMapping("/getDBData")
    @ResponseBody
    public DBConnectionCheckModel getDBData(@RequestParam Long id) {
        System.out.println("Ram Calling - getDBData............... "+id.toString());
        return dbConnectionCheckRepository.findByName(id);
        //return dbConnectionCheckRepository.findBydb_name(DBName);
    }








    //-----------------------------------------------------------------------------------------//

    //@GetMapping("/databaseSelection/delete/{id}")
    //@ResponseBody
    @PostMapping("/databaseSelection/delete/{id}")
    public ResponseEntity<?> deleteDatabaseSelection (@PathVariable Long id,DBConnectionRequest connectionRequest,
                                                      Model model, HttpSession session) {
        dbConnectionCheckRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

/*
    //@PostMapping("/databaseSelection/delete/{id}")
    @GetMapping("/databaseSelection/delete/{id}")
    @ResponseBody
    public ModelAndView deletevalue(@RequestParam Long id) {

        System.out.println("Ram Calling - Delete............... ");

        //dbConnectionCheckRepository.delete(id);
       // return dbConnectionCheckRepository.findByName(DBName);
        //return new ModelAndView("redirect:/Main/databaseSelection");
        return new ModelAndView("redirect:/Main/databaseSelection");
    }

*/



}