package com.example.broker.localaccountbroker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventStoreRepository extends JpaRepository<EventStore, Long> {

    @Query("SELECT e FROM EventStore e WHERE e.id = ?1")
    Optional<EventStore> findEventStoreBy(Long id);

    @Query("SELECT e FROM EventStore e WHERE e.date > ?1")
    Optional<List<EventStore>> findAccountsByUpdatedOnAfter(Date date);
}