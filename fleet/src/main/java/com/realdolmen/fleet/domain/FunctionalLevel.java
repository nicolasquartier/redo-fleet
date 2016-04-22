package com.realdolmen.fleet.domain;


import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
public class FunctionalLevel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Min(value = 0)
    private Integer fLevel;

    /* Used by JPA */
    public FunctionalLevel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getFLevel() {
        return fLevel;
    }

    public void setFLevel(Integer funtionalLevel) {
        this.fLevel = funtionalLevel;
    }
}
