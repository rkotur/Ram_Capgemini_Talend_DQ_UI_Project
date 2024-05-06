package com.example.program.controllers;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.program.models.MetaDataModel;
import com.example.program.Services.MetaDataService;

import jakarta.servlet.http.HttpServletResponse;

// DatabaseConnectionCheck


@Controller
@RequestMapping("/metadatamodels")
public class MetaDataController {

    @Autowired(required=true)
    private MetaDataService metadataService;

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
        return "add-metadatamodel";
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