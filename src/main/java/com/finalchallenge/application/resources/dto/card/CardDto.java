package com.finalchallenge.application.resources.dto.card;

import com.finalchallenge.domain.account.Account;
import com.finalchallenge.domain.card.Card;
import com.finalchallenge.domain.card.Flag;
import com.finalchallenge.domain.card.TypeCard;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
public class CardDto {

    @NotBlank private String name;
    @NotBlank private String flag;
    @NotBlank private String number;
    @NotBlank private String digitCode;
    @NotNull @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal limitBalance;
    @NotNull @Getter private Integer accountId;
    @NotBlank private String typeCard;

    public Card toEntity() {
        return new Card(
                null,
                this.name,
                this.flag,
                this.number,
                this.digitCode,
                this.limitBalance,
                new Account(accountId),
                new TypeCard(typeCard)
        );
    }

    public void update(Card card) {
        card.setName(this.name);
        card.setFlag(Flag.valueOf(this.flag));
        card.setNumber(this.number);
        card.setDigitCode(this.digitCode);
        card.setLimitBalance(this.limitBalance);
        card.setTypeCard(new TypeCard(typeCard));
    }
}
