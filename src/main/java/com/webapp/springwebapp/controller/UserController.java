package com.webapp.springwebapp.controller;

import com.webapp.springwebapp.model.User;
import com.webapp.springwebapp.service.PhoneNumberService;
import com.webapp.springwebapp.service.PhonePlanService;
import com.webapp.springwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;
    private final PhonePlanService phonePlanService;
    private final PhoneNumberService phoneNumberService;

    @Autowired
    public UserController(UserService userService, PhonePlanService phonePlanService, PhoneNumberService phoneNumberService) {
        this.userService = userService;
        this.phonePlanService = phonePlanService;
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping("/")
    public String loginPage() {
        return "index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/admin/edit-user/{id}")
    public String editUserPage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit-user";
    }

    @PostMapping("/admin/update-user")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String role) {
        User user = userService.getUserById(id);
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/users/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/admin/users/add")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/delete-user/{id}")
    public String softDeleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        user.setDeleted(true);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            Model model) {

        if (userService.existsByUsername(username)) {
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            return "register";
        }

        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole("user"); // По умолчанию создаем обычного пользователя

        userService.saveUser(user);
        return "index";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        User user = userService.getUserByNameAndPassword(username, password);

        if (user != null) {
            model.addAttribute("username", username);
            if ("admin".equals(user.getRole())) {
                return "redirect:/admin";
            } else {
                return "redirect:/user/phone-plans";
            }
        } else {
            model.addAttribute("error", "Неверные данные");
            return "index";
        }
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("users", userService.getAllActiveUsers());
        model.addAttribute("phonePlans", phonePlanService.getAllPhonePlans());
        model.addAttribute("phoneNumbers", phoneNumberService.getAllPhoneNumbers());
        return "admin";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
