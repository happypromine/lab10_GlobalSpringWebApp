package com.webapp.springwebapp.repository;

import com.webapp.springwebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNameAndPassword(String name, String password);
    User findByName(String name);
    boolean existsByName(String name);
    User getUserById(Long id);

    List<User> findAllByIsDeletedFalse();
}
