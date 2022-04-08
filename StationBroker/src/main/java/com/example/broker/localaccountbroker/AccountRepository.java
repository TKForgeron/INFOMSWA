package com.example.broker.localaccountbroker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT s FROM Account s WHERE s.uuid = ?1")
    Optional<Account> findAccountByUuid(Long uuid);

    @Query("SELECT MAX(s.updatedOn) FROM Account s")
    Optional<Date> findTopByUpdatedOn();

/*    @Query("SELECT s FROM Account s WHERE s.nfcId = ?1")
    Optional<Account> findAccountByNfcId(Integer nfcId);

    @Query("SELECT s FROM Account s WHERE s.updatedOn > ?1")
    Optional<List<Account>> findAccountsByUpdatedOnAfter(Date updatedOn);*/
}