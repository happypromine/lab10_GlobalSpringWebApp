package com.webapp.springwebapp.service;

import com.webapp.springwebapp.model.PhonePlan;
import com.webapp.springwebapp.repository.PhonePlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhonePlanService {
    private final PhonePlanRepository phonePlanRepository;

    @Autowired
    public PhonePlanService(PhonePlanRepository phonePlanRepository) {
        this.phonePlanRepository = phonePlanRepository;
    }

    public List<PhonePlan> getAllPhonePlans() {
        return phonePlanRepository.findAll();
    }

    public PhonePlan getPhonePlanById(Long id){return phonePlanRepository.findPhonePlanById(id);}

    public PhonePlan savePhonePlan(PhonePlan phonePlan){return phonePlanRepository.save(phonePlan);}

    public void deletePhonePlan(Long id) {
        phonePlanRepository.deleteById(id);
    }

} 