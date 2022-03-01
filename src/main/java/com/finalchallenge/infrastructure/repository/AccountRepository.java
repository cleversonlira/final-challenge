package com.finalchallenge.infrastructure.repository;

import com.finalchallenge.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
