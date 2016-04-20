package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.domain.User;

public class UserMother {

    private User user;

    private UserMother() {
        this.user = new User();
        this.user.setEmail("user@realdolmen.com");
        this.user.setPassword("$2a$10$CmRv22zP8JLu5XkEqX7gyuFoEoh7ll06wabBZrx0zaPFZDROzAlNG");
        this.user.setUsername("user");
        // set some default user data
    }

    public static UserMother init() {
        return new UserMother();
    }

    public User build() {
        return user;
    }

}
