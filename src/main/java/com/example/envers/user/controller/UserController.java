package com.example.envers.user.controller;

import com.example.envers.role.entity.RoleType;
import com.example.envers.user.controller.form.AddUserForm;
import com.example.envers.user.controller.form.ModifyUserForm;
import com.example.envers.user.controller.response.UserResponse;
import com.example.envers.user.controller.response.UserResponseList;
import com.example.envers.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/new")
    public String addUserForm(@ModelAttribute("user") AddUserForm addUserForm) {
        return "user/add-user-form";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") AddUserForm addUserForm) {
        userService.save(addUserForm);
        return "redirect:/";
    }

    @GetMapping
    public String users(Model model) {
        UserResponseList users = UserResponseList.from(userService.findAllWithGroup());
        model.addAttribute("users", users.getItems());
        return "user/users";
    }

    @PostMapping("/{username}/change-role")
    public String changeRole(@PathVariable("username") String username, @RequestParam("roleType") RoleType roleType) {
        userService.modifyRole(username, roleType);
        return "redirect:/";
    }

    @GetMapping("/{username}/modify")
    public String modifyUserForm(@PathVariable("username") String username, Model model) {
        UserResponse user = UserResponse.from(userService.findById(username));
        model.addAttribute("user", user);
        return "/user/modify-user-form";
    }

    @PostMapping("/{username}/modify")
    public String modifyUser(@PathVariable("username") String username, @ModelAttribute("user") ModifyUserForm form) {
        userService.modify(username, form);
        return "redirect:/users";
    }
}
