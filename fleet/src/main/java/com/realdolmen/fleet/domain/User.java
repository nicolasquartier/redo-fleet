package com.realdolmen.fleet.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
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

    @NotBlank
    private String username;

    @ManyToOne
    private FunctionalLevel functionalLevel;

    @NotBlank
    private String password;

    @Column(length = 100)
    @Size(max = 100)
    private String businessUnit;


    private Boolean enabled = true;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public FunctionalLevel getFunctionalLevel() {
        return functionalLevel;
    }

    public void setFunctionalLevel(FunctionalLevel functionalLevel) {
        this.functionalLevel = functionalLevel;
    }


    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
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

    @Transient
    public Integer getCategory() {
        return functionalLevel.getFLevel();
    }
}
