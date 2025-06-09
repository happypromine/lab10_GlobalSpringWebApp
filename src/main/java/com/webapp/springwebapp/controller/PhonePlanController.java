package com.webapp.springwebapp.controller;

import com.webapp.springwebapp.model.PhonePlan;
import com.webapp.springwebapp.service.PhonePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PhonePlanController {
    private final PhonePlanService phonePlanService;

    @Autowired
    public PhonePlanController(PhonePlanService phonePlanService) {
        this.phonePlanService = phonePlanService;
    }

    @GetMapping("/user/phone-plans")
    public String getAllPhonePlans(Model model) {
        model.addAttribute("phonePlans", phonePlanService.getAllPhonePlans());
        return "user";
    }

    @GetMapping("/admin/phone-plans/edit/{id}")
    public String editPhonePlanPage(@PathVariable Long id, Model model) {
        model.addAttribute("plan", phonePlanService.getPhonePlanById(id));
        return "edit-phone-plan";
    }

    @PostMapping("/admin/phone-plans/delete/{id}")
    public String deletePhonePlan(@PathVariable Long id) {
        phonePlanService.deletePhonePlan(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/phone-plans/update")
    public String updatePhonePlan(@RequestParam Long id,
                                  @RequestParam String name,
                                  @RequestParam Double price) {
        PhonePlan plan = phonePlanService.getPhonePlanById(id);
        plan.setName(name);
        plan.setPrice(price);
        phonePlanService.savePhonePlan(plan);
        return "redirect:/admin";
    }

    @GetMapping("/admin/phone-plans/add")
    public String addPhonePlanForm(Model model) {
        model.addAttribute("plan", new PhonePlan());
        return "add-phone-plan";
    }

    @PostMapping("/admin/phone-plans/add")
    public String addPhonePlan(@ModelAttribute PhonePlan plan) {
        phonePlanService.savePhonePlan(plan);
        return "redirect:/admin";
    }
} 