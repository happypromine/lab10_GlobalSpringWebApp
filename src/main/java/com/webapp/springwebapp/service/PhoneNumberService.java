package com.webapp.springwebapp.service;

import com.webapp.springwebapp.model.PhoneNumber;
import com.webapp.springwebapp.model.PhonePlan;
import com.webapp.springwebapp.repository.PhoneNumberRepository;
import com.webapp.springwebapp.repository.PhonePlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberService {
    private final PhoneNumberRepository phoneNumberRepository;
    private final PhonePlanRepository phonePlanRepository;

    @Autowired
    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository, PhonePlanRepository phonePlanRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
        this.phonePlanRepository = phonePlanRepository;
    }

    public List<PhoneNumber> getAllPhoneNumbers() {
        return phoneNumberRepository.findAll();
    }

    public PhoneNumber savePhoneNumber(PhoneNumber phoneNumber){return phoneNumberRepository.save(phoneNumber);}
    public PhoneNumber getPhoneNumberById(Long id){
        return phoneNumberRepository.findPhoneNumberById(id);
    }

    public void createPhoneNumber(String number, String firstName, String lastName, Long planId) {
        PhoneNumber pn = new PhoneNumber();
        pn.setNumber(number);
        pn.setOwnerFirstname(firstName);
        pn.setOwnerLastname(lastName);
        pn.setPhonePlan(phonePlanRepository.findById(planId).orElseThrow());
        phoneNumberRepository.save(pn);
    }

    public void updatePhoneNumber(Long id, String number, String firstName, String lastName, Long planId) {
        PhoneNumber pn = phoneNumberRepository.findById(id).orElseThrow();
        pn.setNumber(number);
        pn.setOwnerFirstname(firstName);
        pn.setOwnerLastname(lastName);
        pn.setPhonePlan(phonePlanRepository.findById(planId).orElseThrow());
        phoneNumberRepository.save(pn);
    }

    public void deletePhoneNumber(Long id) {
        phoneNumberRepository.deleteById(id);
    }
} 