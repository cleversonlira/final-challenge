package com.finalchallenge.domain.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @NoArgsConstructor @Getter
public class TypeCard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Setter
    private Integer id;
    private String name;

    public TypeCard(TypeCardEnum name) {
        this.id = name.ordinal() + 1;
        setName(name);
    }

    public TypeCardEnum getName() {
        return TypeCardEnum.valueOf(name.toUpperCase());
    }

    public void setName(TypeCardEnum name) {
        this.name = name.toString();
    }
}
