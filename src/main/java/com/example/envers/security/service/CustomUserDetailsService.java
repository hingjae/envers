package com.example.envers.security.service;

import com.example.envers.security.dto.CustomUserDetails;
import com.example.envers.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserWithRolesByUsername(username)
                .map(CustomUserDetails::from)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
