package com.finalchallenge.application.resources.dto;

import com.finalchallenge.domain.account.Account;
import com.finalchallenge.domain.card.Card;
import com.finalchallenge.domain.card.TypeCard;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class CardResponseDTO {

    private Integer id;
    private String name;
    private String flag;
    private String number;
    private String digitCode;
    private BigDecimal limitBalance;
    private Account account;
    private TypeCard typeCard;

    public CardResponseDTO(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.flag = card.getFlag().toString();
        this.number = card.getNumber();
        this.digitCode = card.getDigitCode();
        this.limitBalance = card.getLimitBalance();
        this.account = card.getAccount();
        this.typeCard = card.getTypeCard();
    }

    public static List<CardResponseDTO> convertList(List<Card> cards) {
        return cards.stream().map(CardResponseDTO::new).toList();
    }
}
