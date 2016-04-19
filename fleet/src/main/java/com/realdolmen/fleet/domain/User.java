package com.realdolmen.fleet.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;
    @Column(length = 100)
    @Size(max = 100)
    private String firstName;
    @Column(length = 100)
    @Size(max = 100)
    private String lastName;
    @Column(length = 255, nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    @Email
    private String email;
    @Column(length = 10, nullable = false)
    @NotBlank
    private String role = "ROLE_USER";
    @ManyToOne
    private FunctionalLevel functionalLevel;
    @Column(length = 50, nullable = false)
    @NotBlank
    @Size(min = 1, max = 50)
    private String password;
    private boolean active;
    @Column(length = 100)
    @Size(max = 100)
    private String businessUnit;

    private String username;
    private Boolean enabled = true;
    private String authorities;

    /*
    * Used by JPA
    * */
    public User() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setRole(String role) {
        this.role = role;
    }

    public void setFunctionalLevel(FunctionalLevel functionalLevel) {
        this.functionalLevel = functionalLevel;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getRole() {
        return role;
    }

    public FunctionalLevel getFunctionalLevel() {
        return functionalLevel;
    }


    public boolean isActive() {
        return active;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
