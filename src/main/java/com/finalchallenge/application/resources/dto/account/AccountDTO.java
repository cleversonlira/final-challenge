package com.finalchallenge.application.resources.dto.account;

import com.finalchallenge.domain.account.Account;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@Setter
public class AccountDTO {

    @NotBlank @Length(max = 50)
    private String owner;
    @NotBlank @Length(max = 4)
    private String agencyCode;
    @NotBlank @Length(max = 8)
    private String accountCode;
    @NotBlank @Length(max = 1)
    private String digitVerification;
    @CPF
    private String registerId;

    public Account toEntity() {
        return new Account(null, this.owner, this.agencyCode, this.accountCode, this.digitVerification, this.registerId, null);
    }

    public void update(Account account) {
        account.setOwner(this.owner);
        account.setAccountCode(this.accountCode);
        account.setAgencyCode(this.agencyCode);
        account.setDigitVerification(this.digitVerification);
        account.setRegisterId(this.registerId);
    }

}
