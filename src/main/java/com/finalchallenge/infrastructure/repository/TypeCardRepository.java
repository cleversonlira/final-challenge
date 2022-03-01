package com.finalchallenge.infrastructure.repository;

import com.finalchallenge.domain.card.TypeCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeCardRepository extends JpaRepository<TypeCard, Integer> {
}
