package com.webapp.springwebapp.repository;

import com.webapp.springwebapp.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    PhoneNumber findPhoneNumberById(Long id);
}
