package com.realdolmen.fleet.service;

import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AuthService {

    @Autowired
    private UserRepository userRepository;


    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getName() {
        return getAuthentication().getName();
    }

    public User getCurrentUser() {
        return userRepository.findByUsername(getName());
    }
}
