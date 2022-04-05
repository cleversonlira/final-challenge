package com.finalchallenge.application.resources.dto.account;

import com.finalchallenge.domain.account.Account;
import lombok.Getter;

import java.util.List;

@Getter
public class AccountResponseDTO {

    private final Integer id;
    private final String owner;
    private final String agencyCode;
    private final String accountCode;
    private final String digitVerification;
    private final String registerId;

    public AccountResponseDTO(Account account) {
        this.id = account.getId();
        this.owner = account.getOwner();
        this.agencyCode = account.getAgencyCode();
        this.accountCode = account.getAccountCode();
        this.digitVerification = account.getDigitVerification();
        this.registerId = account.getRegisterId();
    }

    public static List<AccountResponseDTO> convertList(List<Account> accounts) {
        return accounts.stream().map(AccountResponseDTO::new).toList();
    }
}
