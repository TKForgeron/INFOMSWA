package com.trip.nfcreaderapp.nfcReader;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface NFCReaderRepository
        extends JpaRepository<BankCard, Long> {

    @Query("SELECT s FROM BankCard s WHERE s.nfcId = ?1")
    Optional<BankCard> findBankCardByNfcId(Integer nfcId);

    // @Query("UPDATE BankCard s SET s.expiryDate =: ?2, s.nfcId = ?3 WHERE s.uuid =
    // ?1")
    // Integer updateBankCardByUuid(Long uuid, LocalDate expiryDate, Integer nfcId);
    @Modifying
    @Query("UPDATE BankCard s SET s.expiryDate = :expiryDate, s.nfcId = :nfcId WHERE s.uuid = :uuid")
    Integer updateBankCardByUuid(@Param("uuid") Long uuid, @Param("expiryDate") LocalDate expiryDate,
            @Param("nfcId") Integer nfcId);
}