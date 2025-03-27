package com.cap_talend.program.controllers.admin;


import com.cap_talend.program.Services.RoleService;
import com.cap_talend.program.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/role")
public class RoleAdminController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getAllRoles(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("role", new Role());
        return "admin/role";
    }

    @GetMapping("/{id}")
    public String getRoleById(@PathVariable Long id, Model model) {
        Optional<Role> role = roleService.findById(id);
        if (role.isPresent()) {
            model.addAttribute("role", role.get());
        } else {
            model.addAttribute("role", new Role());
        }
        model.addAttribute("roles", roleService.findAll());
        return "admin/role";
    }

    @PostMapping
    public String saveRole(@ModelAttribute Role role) {
        roleService.save(role);
        return "redirect:/admin/role";
    }

    @PostMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.deleteById(id);
        return "redirect:/admin/role";
    }
}
