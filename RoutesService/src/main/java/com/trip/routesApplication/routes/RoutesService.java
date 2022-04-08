package com.trip.routesApplication.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoutesService {

    private final RoutesRepository RoutesRepository;

    @Autowired
    public RoutesService(RoutesRepository RoutesRepository) {
        this.RoutesRepository = RoutesRepository;
    }

    public List<Route> getRoutes() {
        return RoutesRepository.findAll();
    }

    public Route findRouteByStations(String stationA, String stationB) {
        Optional<Route> routeOptional = RoutesRepository.findRouteByStationAAndStationB(stationA, stationB);
        if (!routeOptional.isPresent()) {
            throw new IllegalStateException("route does not exist!");
        }
        return routeOptional.get();
    }

    public Route addRoute(Route route) {
        Optional<Route> routeOptional = RoutesRepository.findRouteByStationAAndStationB(route.getStationA(),
                route.getStationB());
        if (routeOptional.isPresent()) {
            throw new IllegalStateException("route already exists!");
        }
        RoutesRepository.save(route);
        return route;
    }

    @Transactional
    public Route updateRoute(Long routeIdCurrent, Route routeToBe) {
        Route routeCurrent = RoutesRepository.findById(routeIdCurrent)
                .orElseThrow(() -> new IllegalStateException("routeId does not exist!"));
        String stationA = routeToBe.getStationA();
        String stationB = routeToBe.getStationB();
        Float price = routeToBe.getPrice();
        String tycoon = routeToBe.getTycoon();
        if (stationA != null && stationA.length() > 0) {
            routeCurrent.setStationA(stationA);
        }
        if (stationB != null && stationB.length() > 0) {
            routeCurrent.setStationB(stationB);
        }
        if (price != null) {
            routeCurrent.setPrice(price);
        }
        if (tycoon != null && tycoon.length() > 0) {
            routeCurrent.setTycoon(tycoon);
        }

        return routeCurrent;

    }

    public Route deleteRoute(Long routeIdCurrent, Route iKnowWhatRouteIAmDeleting) {
        Route routeCurrent = RoutesRepository.findById(routeIdCurrent)
                .orElseThrow(() -> new IllegalStateException("routeId: " + routeIdCurrent + "does not exist!"));
        if (routeCurrent.isLike(iKnowWhatRouteIAmDeleting)) {
            RoutesRepository.deleteById(routeIdCurrent);

        } else {
            throw new IllegalStateException("You are not authorized to delete!");

        }
        return routeCurrent;

    }
}
