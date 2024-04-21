package com.example.envers.envers.service;

import com.example.envers.envers.SearchUserEventHistoryCondition;
import com.example.envers.envers.UserEventHistory;
import com.example.envers.envers.repository.UserEventHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserEventHistoryService {

    private final UserEventHistoryRepository userEventHistoryRepository;

    public List<UserEventHistory> findAll() {
        List<UserEventHistory> userEventHistoryList = userEventHistoryRepository.findUserEventHistory(new SearchUserEventHistoryCondition());
        for (UserEventHistory userEventHistory : userEventHistoryList) {
            System.out.println("userEventHistory = " + userEventHistory);
        }
        return userEventHistoryList;
    }
}
