package com.finalchallenge.infrastructure.repository;

import com.finalchallenge.domain.card.TypeCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeCardRepository extends JpaRepository<TypeCard, Integer> {
    Optional<TypeCard> findByName(String name);
}
