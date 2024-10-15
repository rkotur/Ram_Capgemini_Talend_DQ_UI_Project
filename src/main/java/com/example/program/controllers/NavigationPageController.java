package com.example.program.controllers;

import com.example.program.Services.ETLScheduleService;
import com.example.program.models.ETLScheduleModel;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping("/navigation")
public class NavigationPageController {

    @Autowired
    private ETLScheduleService etlScheduleService;


    @GetMapping("/campaign")
    public String showAllCampaigns(@RequestParam(defaultValue = "0") int page, Model model) {
        System.out.println("-- Step-3 ------  /campaigns --- showAllCampaigns ----------");

        // Assuming you have a service method that supports pagination
        Page<ETLScheduleModel> schedulePage = etlScheduleService.getAllSchedules(PageRequest.of(page, 10)); // 10 records per pag

        model.addAttribute("etlSchedules", schedulePage.getContent()); // Get content for the current page
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", schedulePage.getTotalPages());



        return "/navigation/show_campaign"; // Return the view for displaying all campaigns
    }

    @PostMapping("/save")
    public String saveCreatedCampaign(
            @Valid @ModelAttribute("etlScheduleModel") ETLScheduleModel etlScheduleModel,
            BindingResult result,
            Model model) {

        ETLScheduleModel existing = etlScheduleService.findByCampaignName(etlScheduleModel.getCampaignName());
        if (existing != null) {

            result.rejectValue("campaignname", null, "A campaign with that name already exists.");
            return "show_campaign"; // Return to the form if there are validation errors
        }


        etlScheduleService.save(etlScheduleModel); // Save the new campaign
        return "redirect:/navigation/campaign?success"; // Redirect to the form with success message
    }


    @GetMapping
    public String getAllETLSchedules(Model model) {
        List<ETLScheduleModel> schedules = etlScheduleService.findAll();
        model.addAttribute("schedules", schedules);
        return "show-schedules";
    }

    @GetMapping("/new")
    public String createETLScheduleForm(Model model) {
        model.addAttribute("schedule", new ETLScheduleModel());
        return "/navigation/create_new_campaign";
    }

    @PostMapping
    public String saveETLSchedule(@ModelAttribute ETLScheduleModel schedule) {
        etlScheduleService.save(schedule);
        return "redirect:/etl-schedules";
    }

    @GetMapping("/edit/{id}")
    public String editETLScheduleForm(@PathVariable Long id, Model model) {
        ETLScheduleModel schedule = etlScheduleService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid schedule Id:" + id));
        model.addAttribute("schedule", schedule);
        return "/navigation/create_new_campaign";

    }

    @PostMapping("/update/{id}")
    public String updateETLSchedule(@PathVariable Long id, @ModelAttribute ETLScheduleModel schedule) {
        schedule.setId(id);
        etlScheduleService.save(schedule);
        return "redirect:/navigation/campaign?success";
    }

    @GetMapping("/delete/{id}")
    public String deleteETLSchedule(@PathVariable Long id) {
        etlScheduleService.deleteById(id);
        return "redirect:/navigation/campaign?success";
    }

    private String formatToEST(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        return dateTime.atZone(ZoneId.of("America/New_York")).format(formatter);
    }

}
