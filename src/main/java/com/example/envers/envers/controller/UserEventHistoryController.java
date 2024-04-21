package com.example.envers.envers.controller;

import com.example.envers.envers.SearchUserEventHistoryCondition;
import com.example.envers.envers.service.UserEventHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/user-history")
@Controller
public class UserEventHistoryController {

    private final UserEventHistoryService userEventHistoryService;

    @GetMapping
    public String userHistory(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        SearchUserEventHistoryCondition condition = SearchUserEventHistoryCondition.builder()
                .limit(pageable.getPageSize())
                .offset(pageable.getPageNumber())
                .build();
        userEventHistoryService.findAllByCondition(pageable, condition);
        return "user/user-history";
    }
}
