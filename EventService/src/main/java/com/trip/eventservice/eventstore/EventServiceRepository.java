package com.trip.eventservice.eventstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventServiceRepository
        extends JpaRepository<EventStore, Long> {

    @Query("SELECT e FROM EventStore e WHERE e.id = ?1")
    Optional<EventStore> findEventStoreBy(Long id);

    @Query("SELECT MAX(e.date) FROM EventStore e")
    Optional<Date> findTopByUpdatedOn();

    @Query("SELECT e FROM EventStore e WHERE e.date > ?1")
    Optional<List<EventStore>> findAccountsByUpdatedOnAfter(Date date);
}