package com.trip.billingservice.passengerRoute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BillingRepository
        extends JpaRepository<PassengerRoute, Long> {

    @Query("SELECT route FROM PassengerRoute route WHERE route.uuid = ?1")
    Optional<PassengerRoute> findPassengerRouteBy(Long uuid);

    @Query("SELECT MAX(route.endDate) FROM PassengerRoute route")
    Optional<Date> findTopByUpdatedOn();
}