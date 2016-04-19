package com.realdolmen.fleet.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class FunctionalLevel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private int fLevel;

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

    public void setFLevel(int funtionalLevel) {
        this.fLevel = funtionalLevel;
    }

    public int getFLevel() {
        return fLevel;
    }
}
