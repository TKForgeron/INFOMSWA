package com.trip.subscriptionservice.subscription;

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

        // @Query("SELECT FIRST s FROM Subscription s WHERE s.description = :?1")
        // Optional<Subscription> findSubscriptionByDescription(String description);

}
