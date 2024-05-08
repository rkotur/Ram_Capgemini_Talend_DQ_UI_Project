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
// DatabaseConnectionCheck


@Controller
@RequestMapping("/metadatamodels")
public class MetaDataController {

    @Autowired(required=true)
    private MetaDataService metadataService;
    private final SchemaRepository schemaRepository;

    public MetaDataController(SchemaRepository schemaRepository) {
        this.schemaRepository = schemaRepository;
    }


    @GetMapping("/getAll")
    public String getAll(Model model) {
        List<MetaDataModel> stlist = metadataService.getAll();
        model.addAttribute("metadatamodels", stlist);
        return "metadatamodels";
    }

    @GetMapping("/addNew")
    public String newMetaDataModel(Model model) {
        MetaDataModel metadatamodel = new MetaDataModel();
        model.addAttribute("metadatamodel", metadatamodel);

        List<String> schemas = new ArrayList<String>();
        schemas.add("option 1");
        schemas.add("option 2");
        schemas.add("option 3");
        model.addAttribute("schemas", schemas);


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
        return "redirect:/metadatamodels/getAll";
    }

    @PostMapping("/saveNew")
    public String insertMetaDataModel(
            @ModelAttribute(value="metadatamodel") MetaDataModel metadatamodel) throws IOException {

        metadataService.insert(metadatamodel);
        return "redirect:/metadatamodels/getAll";
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