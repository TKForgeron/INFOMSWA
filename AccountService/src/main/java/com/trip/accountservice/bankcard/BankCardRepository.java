package com.trip.serviceterminal.bankcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankCardRepository
        extends JpaRepository<BankCard, Long> {

    @Query("SELECT s FROM AccountDB s WHERE s.nfcId = ?1")
    Optional<BankCard> findBankCardByNfcId(Integer nfcId);
}