package com.finalchallenge.domain.card;

import com.finalchallenge.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Card {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String flag;
    private String number;

    @Column(name = "digit_code")
    private String digitCode;

    @Column(name = "limit_balance")
    private BigDecimal limitBalance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "type_card_id")
    private TypeCard typeCard;

    public Flag getFlag() {
        return Flag.valueOf(flag.toUpperCase());
    }

    public void setFlag(Flag flag) {
        this.flag = flag.toString();
    }

}
