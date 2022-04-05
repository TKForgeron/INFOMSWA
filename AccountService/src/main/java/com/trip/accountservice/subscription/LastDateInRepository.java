package com.trip.accountservice.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface LastDateInRepository extends JpaRepository<Account, Long> {

    @Query("SELECT MAX(s.updatedOn) FROM Account s")
    Optional<Date> findTopByUpdatedOn();
}