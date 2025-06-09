package com.webapp.springwebapp.controller;

import com.webapp.springwebapp.model.PhonePlan;
import com.webapp.springwebapp.service.PhonePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phone-plans")
public class PhonePlanRestController {
    private final PhonePlanService phonePlanService;

    @Autowired
    public PhonePlanRestController(PhonePlanService phonePlanService) {
        this.phonePlanService = phonePlanService;
    }

    @GetMapping
    public ResponseEntity<List<PhonePlan>> getAllPhonePlans() {
        return ResponseEntity.ok(phonePlanService.getAllPhonePlans());
    }
} 