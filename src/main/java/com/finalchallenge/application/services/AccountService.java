package com.finalchallenge.application.services;

import com.finalchallenge.application.resources.dto.account.AccountDTO;
import com.finalchallenge.application.resources.dto.account.AccountResponseDTO;
import com.finalchallenge.domain.account.Account;
import com.finalchallenge.infrastructure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    public ResponseEntity<List<AccountResponseDTO>> getAll() {
        return ResponseEntity.ok(AccountResponseDTO.convertList(repository.findAll()));
    }

    public ResponseEntity<AccountResponseDTO> getById(Integer id) {
        Optional<Account> accountOp = repository.findById(id);
        return accountOp.isPresent()
                ? ResponseEntity.ok(new AccountResponseDTO(accountOp.get()))
                : ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<AccountResponseDTO> save(AccountDTO dto, UriComponentsBuilder uriBuilder) {
        Account account = repository.save(dto.toEntity());
        URI uri = uriBuilder.path("/account/{id}").buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(uri).body(new AccountResponseDTO(account));
    }

    @Transactional
    public ResponseEntity<?> deleteById(Integer id) {
        Optional<Account> accountOp = repository.findById(id);
        if (accountOp.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<AccountResponseDTO> update(Integer id, AccountDTO dto) {
        Optional<Account> accountOp = repository.findById(id);
        if (accountOp.isPresent()) {
            dto.update(accountOp.get());
            return ResponseEntity.ok(new AccountResponseDTO(accountOp.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
