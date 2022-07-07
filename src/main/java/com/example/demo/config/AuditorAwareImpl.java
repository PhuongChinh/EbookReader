package com.example.demo.config;

import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private UserRepository userRepository;
    //Tokenstore

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable("Tien dep trai");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
//            return null;
//        }
//        String username = authentication.getName();
//        return ((MyUserDetails) authentication.getPrincipal()).getUser();
    }
}
