package com.nfcReaderApp.nfcReader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NFCReaderRepository
        extends JpaRepository<NFCReader, Long> {

    @Query("SELECT s FROM Accounts-NFCReader s WHERE s.nfcId = ?1")
    Optional<NFCReader> findAccountByNfcId(Integer nfcId);
}
