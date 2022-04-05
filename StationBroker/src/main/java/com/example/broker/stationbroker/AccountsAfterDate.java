package com.example.broker.stationbroker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AccountsAfterDate
        extends JpaRepository<Account, Long> {

    @Query("SELECT s FROM Account s WHERE s.updatedOn > ?1")
    Optional<BankCard> findAllByUpdatedOnAfter(Date date);
}
