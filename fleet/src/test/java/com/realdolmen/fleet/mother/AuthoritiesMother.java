package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.domain.Authorities;

public class AuthoritiesMother {

    Authorities authorities;

    private AuthoritiesMother() {
        this.authorities = new Authorities();
        this.authorities.setUsername("dummie");
        this.authorities.setAuthority("ROLE_USER");
        // set some default user data
    }

    public static AuthoritiesMother init() {
        return new AuthoritiesMother();
    }

    public Authorities build() {
        return authorities;
    }
}
