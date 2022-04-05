package com.finalchallenge.application.resources.dto.card;

import com.finalchallenge.domain.card.TypeCard;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class TypeCardDTO {

    @NotBlank
    private String name;

    public TypeCard toEntity() {
        return new TypeCard(name.toUpperCase());
    }

    public void update(TypeCard typeCard) {
        typeCard.setName(nameValidated());
    }

    private String nameValidated() {
        return this.name.toUpperCase().replace("-", "_").replace(" ", "_");
    }
}
