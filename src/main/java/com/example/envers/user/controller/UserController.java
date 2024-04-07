package com.example.envers.user.controller;

import com.example.envers.user.controller.form.AddUserForm;
import com.example.envers.user.controller.response.UserResponse;
import com.example.envers.user.entity.User;
import com.example.envers.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/users")
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/new")
    public String addUserForm(@ModelAttribute("user") AddUserForm addUserForm) {
        return "add-user-form";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") AddUserForm addUserForm) {
        userService.save(addUserForm);
        return "redirect:/users";
    }

    @GetMapping
    public String users(Model model) {
        List<UserResponse> users = userService.findAll().stream()
                .map(UserResponse::from)
                .toList();

        model.addAttribute("users", users);
        return "users";
    }
}
