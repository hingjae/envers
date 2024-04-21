package com.example.envers.envers.service;

import com.example.envers.envers.SearchUserEventHistoryCondition;
import com.example.envers.envers.UserEventHistory;
import com.example.envers.envers.repository.UserEventHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserEventHistoryService {

    private final UserEventHistoryRepository userEventHistoryRepository;

    public Page<UserEventHistory> findAllByCondition(Pageable pageable, SearchUserEventHistoryCondition condition) {
        Page<UserEventHistory> userEventHistoryPage = userEventHistoryRepository.findAllByCondition(pageable, condition);
        List<UserEventHistory> content = userEventHistoryPage.getContent();
        for (UserEventHistory userEventHistory : content) {
            System.out.println("userEventHistory = " + userEventHistory);
        }
        return userEventHistoryPage;
    }
}
