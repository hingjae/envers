package com.example.envers.user.envers;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserRevisionEntityListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
        UserRevision userRevision = (UserRevision) o;
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

    }
}
