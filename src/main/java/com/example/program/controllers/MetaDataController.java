package com.example.program.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.program.models.MetaDataModel;
import com.example.program.Services.MetaDataService;

import jakarta.servlet.http.HttpServletResponse;
import com.example.program.repository.SchemaRepository;
import com.example.program.repository.DQ_RulesRepository;

// DatabaseConnectionCheck


@Controller
@RequestMapping("/metadata")
public class MetaDataController {

    @Autowired(required=true)
    private MetaDataService metadataService;
    private DQ_RulesRepository dq_rulesrepository;
    private final SchemaRepository schemaRepository;

    public MetaDataController(SchemaRepository schemaRepository) {
        this.schemaRepository = schemaRepository;
    }

/*
    @GetMapping("/test")
    public String getAll() {
        return "Metadatamodels";
    }
*/


    //@RequestMapping(value="/getAll", method = RequestMethod.POST)
    @GetMapping("/getAll")
    public String getAll(Model model) {

        MetaDataModel metadatamodel = new MetaDataModel();
        model.addAttribute("metadatamodel", metadatamodel);
//-----------------------------------------------------------------------



        List<String> schemaNames =  new ArrayList<>();
        schemaRepository.getSchemas().forEach(e-> schemaNames.add(e.getName()));
        model.addAttribute("schemaNames", schemaNames);

        List<String> tableNames =  new ArrayList<>();
        schemaRepository.getTables("spoton").forEach(e-> tableNames.add(e.getName()));
        model.addAttribute("tableNames", tableNames);

        List<String> columnsNames =  new ArrayList<>();
        schemaRepository.getColumns("spoton","loading").forEach(e-> columnsNames.add(e.getName()));
        model.addAttribute("columnsNames", columnsNames);

        /*
        List<String> ruleMetaNames =  new ArrayList<>();
        dq_rulesrepository.getRules().forEach(e-> ruleMetaNames.add(e.getName()));
        model.addAttribute("ruleMetaNames", ruleMetaNames);
        */


       List<String> ruleMetaNames = new ArrayList<>();
        ruleMetaNames.add("Age");
        ruleMetaNames.add("AlphaNumeric Check");
        ruleMetaNames.add("Amex_card");
        ruleMetaNames.add("Avg Value");
        ruleMetaNames.add("Bank Account number");
        ruleMetaNames.add("BLANK Check");
        ruleMetaNames.add("Cargo Weight");
        ruleMetaNames.add("DISTINCT Count");
        ruleMetaNames.add("Email");
        ruleMetaNames.add("Email Pattern Check");
        ruleMetaNames.add("Master_card");
        ruleMetaNames.add("Max Value");
        ruleMetaNames.add("Min Value");
        ruleMetaNames.add("NULL Check");
        ruleMetaNames.add("Numeric Check");
        ruleMetaNames.add("Passport number");
        ruleMetaNames.add("Phone number");
        ruleMetaNames.add("ROW Count");
        ruleMetaNames.add("Special Char Check");
        ruleMetaNames.add("Start Lower Case");
        ruleMetaNames.add("Start Upper Case");
        ruleMetaNames.add("String Matches");
        ruleMetaNames.add("Value Contains");
        ruleMetaNames.add("Value Frequency");
        ruleMetaNames.add("Value Greater Than Equals to");
        ruleMetaNames.add("Value Not Contains");
        ruleMetaNames.add("Visa_card");
        model.addAttribute("ruleMetaNames", ruleMetaNames);


// -------------------------------------------------------------------------
        List<MetaDataModel> stlist = metadataService.getAll();
        model.addAttribute("metadatamodels", stlist);

        return "Metadatamodels";
    }



    @GetMapping("/addNew")
    public String newMetaDataModel(Model model) {
        MetaDataModel metadatamodel = new MetaDataModel();
        model.addAttribute("metadatamodel", metadatamodel);







        //List<String> databaseNames = schemaRepository.findAllschema_name();
        // Add the list to the model
        // model.addAttribute("databaseNames", databaseNames);

        List<String> schemaNames =  new ArrayList<>();
        schemaRepository.getSchemas().forEach(e-> schemaNames.add(e.getName()));
        model.addAttribute("schemaNames", schemaNames);

        List<String> tableNames =  new ArrayList<>();
        schemaRepository.getTables("spoton").forEach(e-> tableNames.add(e.getName()));
        model.addAttribute("tableNames", tableNames);

        List<String> columnsNames =  new ArrayList<>();
        schemaRepository.getColumns("spoton","loading").forEach(e-> columnsNames.add(e.getName()));
        model.addAttribute("columnsNames", columnsNames);

/*
        List<String> ruleNames = new ArrayList<>();
        schemaRepository.getRules().forEach(e-> ruleNames.add(e.getName()));
        //schemaRepository.getSchemas().forEach(e-> ruleNames.add(e.getName()));
        model.addAttribute("ruleNames", ruleNames);

        System.out.println("--- Ram printinh values ---- ");
        for (String value : ruleNames) {
            System.out.println(value);
        }
*/

/*
       List<String> ruleMetaNames = new ArrayList<>();
        ruleMetaNames.add("Null_Count");
        ruleMetaNames.add("Blank_Count");
        ruleMetaNames.add("Distinct_Count");
        ruleMetaNames.add("Start_Lower_Case");
        ruleMetaNames.add("Start_Upper_Case");
        ruleMetaNames.add("Alpha_Numeric_Chk");
        ruleMetaNames.add("Only_Numeric_Chk");
        ruleMetaNames.add("Email_Pattern_Chk");
        ruleMetaNames.add("Special_Char_Chk");
        ruleMetaNames.add("Min_value");
        ruleMetaNames.add("Max_value");
        ruleMetaNames.add("Avg");
        model.addAttribute("ruleMetaNames", ruleMetaNames);
*/

        return "add-metadatamodel";
    }

    @GetMapping("/getTables")
    public @ResponseBody String getTablesFunc(@RequestParam String SchemaName)
    {
        String json = null;
        List<String> tableNames =  new ArrayList<>();
        schemaRepository.getTables(SchemaName).forEach(e-> tableNames.add(e.getName()));

        try {
            json = new ObjectMapper().writeValueAsString(tableNames);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @GetMapping("/getColumns")
    public @ResponseBody String getColumnsFunc(@RequestParam String SchemaName,@RequestParam String TableName)
    {
        String json = null;
        List<String> ColumnNames =  new ArrayList<>();
        schemaRepository.getColumns(SchemaName,TableName).forEach(e-> ColumnNames.add(e.getName()));

        try {
            json = new ObjectMapper().writeValueAsString(ColumnNames);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


    @GetMapping("/edit/{id}")
    public String editMetaDataModel(@PathVariable("id") int id, Model model, HttpServletResponse response)
            throws IOException {
        MetaDataModel metadatamodel = metadataService.getMetaDataModel(id);
        metadatamodel.setUpdatedOn(metadatamodel.getUpdatedOn());
        model.addAttribute("metadatamodel", metadatamodel);
        return "edit-metadatamodel";
    }

    @PostMapping("/delete/{id}")
    public String deleteMetaDataModel(@PathVariable("id") int id) {
        metadataService.delete(id);
        return "redirect:/metadata/getAll";
    }

    @PostMapping("/saveNew")
    public String insertMetaDataModel(
            @ModelAttribute(value="metadatamodel") MetaDataModel metadatamodel) throws IOException {

        metadataService.insert(metadatamodel);
        return "redirect:/metadata/getAll";
    }

    @PostMapping("/update/{id}")
    public String updateMetaDataModel(
            @PathVariable("id") int id,
            @ModelAttribute(value="metadatamodel") MetaDataModel metadatamodel) throws IOException
    {

        metadataService.update(id, metadatamodel);
        return "redirect:/metadatamodels/getAll";
    }
}