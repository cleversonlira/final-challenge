package com.finalchallenge.application.resources.dto.card;

import com.finalchallenge.domain.card.TypeCard;
import lombok.Getter;

import java.util.List;

@Getter
public class TypeCardResponseDTO {


    private final Integer id;
    private final String name;

    public TypeCardResponseDTO(TypeCard typeCard) {
        this.id = typeCard.getId();
        this.name = typeCard.getName().toString();
    }

    public static List<TypeCardResponseDTO> convertList(List<TypeCard> typeCards) {
        return typeCards.stream().map(TypeCardResponseDTO::new).toList();
    }
}
