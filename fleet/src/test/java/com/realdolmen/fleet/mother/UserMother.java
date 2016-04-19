package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.domain.User;

public class UserMother {

    User user;

    private UserMother() {
        this.user = new User();
        this.user.setEmail("user@realdolmen.com");
        this.user.setAuthorities("ROLE_USER");
        this.user.setPassword("123456");
        this.user.setUsername("dummie");
        // set some default user data
    }

    public static UserMother init() {
        return new UserMother();
    }

    public User build() {
        return user;
    }

}
