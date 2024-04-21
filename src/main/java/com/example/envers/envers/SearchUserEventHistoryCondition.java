package com.example.envers.envers;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchUserEventHistoryCondition {
    private int limit;

    private int offset;
}
