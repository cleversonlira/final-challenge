package com.finalchallenge.application.services;

import com.finalchallenge.application.resources.dto.card.TypeCardResponseDTO;
import com.finalchallenge.application.resources.dto.card.TypeCardDTO;
import com.finalchallenge.domain.card.Card;
import com.finalchallenge.domain.card.TypeCard;
import com.finalchallenge.infrastructure.repository.CardRepository;
import com.finalchallenge.infrastructure.repository.TypeCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class TypeCardService {

    @Autowired
    TypeCardRepository repository;
    @Autowired
    CardRepository cardRepository;

    public ResponseEntity<List<TypeCardResponseDTO>> getAll() {
        return ResponseEntity.ok(TypeCardResponseDTO.convertList(repository.findAll()));
    }

    public ResponseEntity<TypeCardResponseDTO> getById(Integer id) {
        Optional<TypeCard> typeCardOp = repository.findById(id);
        return typeCardOp.isPresent()
                ? ResponseEntity.ok(new TypeCardResponseDTO(typeCardOp.get()))
                : ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<TypeCardResponseDTO> save(TypeCardDTO dto, UriComponentsBuilder uriBuilder) {
        exists(dto);
        TypeCard typeCard = repository.save(dto.toEntity());
        URI uri = uriBuilder.path("/TypeCard/{id}").buildAndExpand(typeCard.getId()).toUri();
        return ResponseEntity.created(uri).body(new TypeCardResponseDTO(typeCard));
    }

    @Transactional
    public ResponseEntity<?> deleteById(Integer id) {
        Optional<TypeCard> typeCardOp = repository.findById(id);
        if (typeCardOp.isPresent()) {
            hasAssociatedCard(typeCardOp.get());
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<TypeCardResponseDTO> update(Integer id, TypeCardDTO dto) {
        Optional<TypeCard> typeCardOp = repository.findById(id);
        if (typeCardOp.isPresent()) {
            dto.update(typeCardOp.get());
            return ResponseEntity.ok(new TypeCardResponseDTO(typeCardOp.get()));
        }
        return ResponseEntity.notFound().build();
    }

    private void hasAssociatedCard(TypeCard typeCard) {
        Optional<Card> cardOptional = cardRepository
                .findAll().stream()
                .filter(card ->
                        card.getTypeCard().getId().equals(typeCard.getId())
                ).findFirst();
        if (cardOptional.isPresent())
            throw new IllegalArgumentException("You cannot delete this type as there are cards associated with it.");
    }

    private void exists(TypeCardDTO dto) {
        if (repository.findByName(dto.getName()).isPresent())
            throw new IllegalArgumentException("This type of card already exists!");
    }
}
