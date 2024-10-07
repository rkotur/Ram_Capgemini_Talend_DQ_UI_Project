package com.example.program.controllers;

import com.example.program.Services.ETLScheduleService;
import com.example.program.models.ETLScheduleModel;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/navigation")
public class NavigationPageController {

    private ETLScheduleService etlScheduleService;

    @GetMapping("/campaign")
    public String capaign(HttpSession session) {
        return "campaign_page";
    }

    @GetMapping("/create_campaign")
    public String create_capaign(HttpSession session,Model model) {

        ETLScheduleModel etlschedulemodel = new ETLScheduleModel();
        model.addAttribute("etlschedulemodel", etlschedulemodel);

        return "create_campaign";
    }

    @GetMapping("/existing_campaign")
    public String existing_campaign(HttpSession session) {
        return "existing_campaign";
    }

    @PostMapping("/create_campaign/save")
    public String create_campaign_save(@Valid @ModelAttribute("etlschedulemodel") ETLScheduleModel etlschedulemodel,
                               BindingResult result,
                               Model model){


        System.out.println("-------------------- Ram --------------------------- Step-1.......");
        System.out.println(etlschedulemodel.getCampaign_name());
        System.out.println("----------------------------------------------- Step2.......");
/*
        ETLScheduleModel existing = etlScheduleService.findBycampaign_name(etlschedulemodel.getCampaign_name());

        if (existing != null) {
            result.rejectValue("campaign_name", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("etlschedule", etlschedulemodel);
            return "create_campaign";
        }
*/

        etlScheduleService.saveETLSchedule(etlschedulemodel);
        return "redirect:/navigation/create_campaign?success";
    }


}
