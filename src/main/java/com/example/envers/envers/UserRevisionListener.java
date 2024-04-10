package com.example.envers.envers;

import com.example.envers.security.dto.CustomUserDetails;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class UserRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
        UserRevision userRevision = (UserRevision) o;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication) && authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            userRevision.setModifiedBy(customUserDetails.getUsername());
        }
    }
}
