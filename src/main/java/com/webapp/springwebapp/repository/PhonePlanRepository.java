package com.webapp.springwebapp.repository;

import com.webapp.springwebapp.model.PhonePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhonePlanRepository extends JpaRepository<PhonePlan, Long> {
    PhonePlan findPhonePlanById(Long id);
}
