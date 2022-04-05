
package com.finalchallenge.domain.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.finalchallenge.domain.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_owner")
    private String owner;

    @Column(name = "agency_code")
    private String agencyCode;

    @Column(name = "account_code", unique = true)
    private String accountCode;

    @Column(name = "digit_verification")
    private String digitVerification;

    @Column(name = "register_id", unique = true)
    private String registerId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Card> cards;

    public Account(Integer accountId) {
        this.id = accountId;
    }
}
