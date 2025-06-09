package com.webapp.springwebapp.controller;

import com.webapp.springwebapp.model.PhoneNumber;
import com.webapp.springwebapp.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phone-numbers")
public class PhoneNumberRestController {
    private final PhoneNumberService phoneNumberService;

    @Autowired
    public PhoneNumberRestController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping
    public ResponseEntity<List<PhoneNumber>> getAllPhoneNumbers() {
        return ResponseEntity.ok(phoneNumberService.getAllPhoneNumbers());
    }


} 