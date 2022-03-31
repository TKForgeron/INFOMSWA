package com.example.demo.nfcReader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NFCReaderRepository
        extends JpaRepository<BankCard, Long> {

    @Query("SELECT s FROM BankCard s WHERE s.nfcId = ?1")
    Optional<NFCReaderRepository> findBankCardByNfcId(Integer nfcId);
}