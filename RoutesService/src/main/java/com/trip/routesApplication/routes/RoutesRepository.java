package com.trip.routesApplication.routes;

// import org.springframework.data.jpa.repository.Modifying;
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

//    @Query("SELECT r FROM Routes r WHERE r.stationA = :stationA AND r.stationB = :stationB OR SELECT r FROM Routes r WHERE r.stationA = :stationB AND r.stationB = :stationA")
//    Optional<Route> findRouteByStations(@Param("stationA") String stationA, @Param("stationB") String stationB);

    @Query("SELECT r FROM Route r WHERE r.stationA = :stationA AND r.stationB = :stationB OR r.stationA = :stationB AND r.stationB = :stationA")
    Optional<Route> findRouteByStationAAndStationB(@Param("stationA") String stationA, @Param("stationB") String stationB);

}
