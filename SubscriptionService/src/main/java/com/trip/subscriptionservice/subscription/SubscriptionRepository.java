package com.trip.subscriptionservice.subscription;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository
                extends JpaRepository<Subscription, Long> {

        @Query("SELECT r FROM Subscription r WHERE r.stationA = :stationA AND r.stationB = :stationB OR r.stationA = :stationB AND r.stationB = :stationA")
        Optional<Subscription> findSubscriptionByStationAAndStationB(@Param("stationA") String stationA,
                        @Param("stationB") String stationB);

}
