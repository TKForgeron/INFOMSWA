package com.trip.accountservice.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AccountRepository
        extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.uuid = ?1")
    Optional<Account> findAccountByUuid(Long uuid);
    Optional<Account> deleteAccountByUuid(Long uuid);


    @Query("SELECT MAX(s.updatedOn) FROM Account s")
    Optional<Date> findTopByUpdatedOn();

}