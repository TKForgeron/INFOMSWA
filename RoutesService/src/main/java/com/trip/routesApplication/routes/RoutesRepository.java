package com.trip.routesApplication.routes;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoutesRepository
        extends JpaRepository<Route, Long> {

    @Query("SELECT r FROM Route r WHERE r.stationA = :stationA AND r.stationB = :stationB OR r.stationA = :stationB AND r.stationB = :stationA")
    Optional<Route> findRouteByStationAAndStationB(@Param("stationA") String stationA,
            @Param("stationB") String stationB);

    // @Modifying
    // @Query("UPDATE Route r SET r.stationA = :stationA, r.stationB = :stationB,
    // r.price = :price, r.tycoon = :tycoon WHERE r.id = :id")
    // Optional<Route> updateRoute(@Param("id") Long id,
    // @Param("stationA") String stationA, @Param("stationB") String stationB,
    // @Param("price") Float price,
    // @Param("tycoon") String tycoon);

}
