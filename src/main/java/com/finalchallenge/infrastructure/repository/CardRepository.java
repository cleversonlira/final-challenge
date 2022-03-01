package com.finalchallenge.infrastructure.repository;

import com.finalchallenge.domain.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
