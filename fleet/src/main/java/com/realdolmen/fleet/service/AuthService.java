package com.realdolmen.fleet.service;

import com.realdolmen.fleet.domain.User;
import org.springframework.security.core.Authentication;

public interface AuthService {

    Authentication getAuthentication();

    String getName();

    User getCurrentUser();
}
