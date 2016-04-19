package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.domain.enums.OptionType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "caroption")
public class Option implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    @Column(length = 300)
    @Size(max = 300)
    private String description;

    @Enumerated(EnumType.STRING)
    private OptionType type;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(OptionType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

   public Car getCar() {
        return car;
    }

    public String getDescription() {
        return description;
    }

    public OptionType getType() {
        return type;
    }
}
