package com.webapp.springwebapp.controller;

import com.webapp.springwebapp.model.PhoneNumber;
import com.webapp.springwebapp.model.PhonePlan;
import com.webapp.springwebapp.service.PhoneNumberService;
import com.webapp.springwebapp.service.PhonePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PhoneNumberController {
    private final PhoneNumberService phoneNumberService;
    private final PhonePlanService phonePlanService;

    @Autowired
    public PhoneNumberController(PhoneNumberService phoneNumberService, PhonePlanService phonePlanService) {
        this.phoneNumberService = phoneNumberService;
        this.phonePlanService = phonePlanService;
    }

    @GetMapping("/admin/phone-numbers")
    public String getAllPhoneNumbers(Model model) {
        model.addAttribute("phoneNumbers", phoneNumberService.getAllPhoneNumbers());
        return "admin";
    }

    @GetMapping("/admin/phone-numbers/add")
    public String addPhoneNumberForm(Model model) {
        model.addAttribute("phoneNumber", new PhoneNumber());
        model.addAttribute("phonePlans", phonePlanService.getAllPhonePlans());
        return "add-phone-number";
    }

    @PostMapping("/admin/phone-numbers/add")
    public String addPhoneNumber(@ModelAttribute PhoneNumber phoneNumber, @RequestParam Long planId) {
        phoneNumber.setPhonePlan(phonePlanService.getPhonePlanById(planId));
        phoneNumberService.savePhoneNumber(phoneNumber);
        return "redirect:/admin";
    }

    @PostMapping("/admin/phone-numbers/update")
    public String updatePhoneNumber(
            @RequestParam Long id,
            @RequestParam String number,
            @RequestParam String ownerFirstname,
            @RequestParam String ownerLastname,
            @RequestParam Long phonePlanId
    ) {
        phoneNumberService.updatePhoneNumber(id, number, ownerFirstname, ownerLastname, phonePlanId);
        return "redirect:/admin";
    }

    @PostMapping("/admin/phone-numbers/delete/{id}")
    public String deletePhoneNumber(@PathVariable Long id) {
        phoneNumberService.deletePhoneNumber(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/phone-numbers/edit/{id}")
    public String editPhoneNumberPage(@PathVariable Long id, Model model) {
        PhoneNumber number = phoneNumberService.getPhoneNumberById(id);
        List<PhonePlan> plans = phonePlanService.getAllPhonePlans();

        model.addAttribute("number", number);
        model.addAttribute("phonePlans", plans);
        return "edit-phone-number";
    }

} 