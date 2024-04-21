package com.example.envers.envers.revision;

import com.example.envers.security.dto.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
        Revision revision = (Revision) o;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication) && authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            revision.setModifiedBy(customUserDetails.getUsername());
            revision.setAuthorities(
                    customUserDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(", "))
            );
        }

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(requestAttributes)) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            revision.setGroupName(request.getParameter("groupName"));
        }

        revision.setModifiedAt(LocalDateTime.now());
    }
}
