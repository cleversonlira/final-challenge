package com.finalchallenge.application.services;

import com.finalchallenge.application.resources.dto.CardDto;
import com.finalchallenge.application.resources.dto.CardResponseDTO;
import com.finalchallenge.domain.account.Account;
import com.finalchallenge.domain.card.Card;
import com.finalchallenge.infrastructure.repository.AccountRepository;
import com.finalchallenge.infrastructure.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired CardRepository repository;
    @Autowired AccountRepository accountRepository;

    public ResponseEntity<List<CardResponseDTO>> getAll() {
        return ResponseEntity.ok(CardResponseDTO.convertList(repository.findAll()));
    }

    public ResponseEntity<CardResponseDTO> getById(Integer id) {
        Optional<Card> cardOp = repository.findById(id);
        return cardOp.isPresent()
                ? ResponseEntity.ok(new CardResponseDTO(cardOp.get()))
                : ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<CardResponseDTO> save(CardDto dto, UriComponentsBuilder uriBuilder) {
        Optional<Account> accountOp = accountRepository.findById(dto.getAccountId());
        validateAccount(dto, accountOp);
        Card card = repository.save(dto.toEntity());
        card.setAccount(accountOp.get());
        URI uri = uriBuilder.path("/card/{id}").buildAndExpand(card.getId()).toUri();
        return ResponseEntity.created(uri).body(new CardResponseDTO(card));
    }

    @Transactional
    public ResponseEntity<?> deleteById(Integer id) {
        Optional<Card> cardOp = repository.findById(id);
        if (cardOp.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<CardResponseDTO> update(Integer id, CardDto dto) {
        Optional<Card> cardOp = repository.findById(id);
        if (cardOp.isPresent()) {
            dto.update(cardOp.get());
            return ResponseEntity.ok(new CardResponseDTO(cardOp.get()));
        }
        return ResponseEntity.notFound().build();
    }

    private void validateAccount(CardDto dto, Optional<Account> accountOp) {
        if (accountOp.isEmpty()) throw new IllegalArgumentException("Account with id: " + dto.getAccountId() + " not found!");
    }
}
