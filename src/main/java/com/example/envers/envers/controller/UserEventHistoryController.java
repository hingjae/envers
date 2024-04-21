package com.example.envers.envers.controller;

import com.example.envers.envers.service.UserEventHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/user-history")
@Controller
public class UserEventHistoryController {

    private final UserEventHistoryService userEventHistoryService;

    @GetMapping
    public String userHistory() {
        userEventHistoryService.findAll();
        return "user/user-history";
    }
}
