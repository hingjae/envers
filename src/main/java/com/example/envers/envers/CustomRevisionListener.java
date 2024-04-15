package com.example.envers.envers;

import com.example.envers.security.dto.CustomUserDetails;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class CustomRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
        Revision revision = (Revision) o;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication) && authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            revision.setModifiedBy(customUserDetails.getUsername());
        }
    }
}
