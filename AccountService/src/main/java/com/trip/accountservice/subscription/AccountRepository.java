package com.trip.accountservice.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AccountRepository
        extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.nfcId = ?1")
    Optional<Account> findAccountByNfcId(Integer nfcId);

    @Query("SELECT MAX(s.updatedOn) FROM Account s")
    Optional<Date> findTopByUpdatedOn();
}