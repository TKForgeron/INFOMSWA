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

    public Optional<Route> findRouteByStations(String stationA, String stationB) {
        return RoutesRepository.findRouteByStationAAndStationB(stationA, stationB);
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
        // RoutesRepository.updateRoute(routeIdCurrent, routeToBe.getStationA(),
        // routeToBe.getStationB(),
        // routeToBe.getPrice(), routeToBe.getTycoon());
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
    // public Boolean isValid(Integer nfcId) {
    // Optional<Routes> accountOptional = RoutesRepository
    // .findAccountByNfcId(nfcId);
    // if (accountOptional.isPresent()) {
    // return true;
    // }
    // return false;
    // }

    // public void addNewStudent(Routes Routes) {
    // Optional<Routes> studentOptional = RoutesRepository
    // .findStudentByEmail(Routes.getEmail());
    // if (studentOptional.isPresent()) {
    // throw new IllegalStateException("email taken");
    // }
    // RoutesRepository.save(Routes);
    // }

    // public void deleteStudent(Long studentId) {
    // boolean exists = RoutesRepository.existsById(studentId);
    // if (!exists) {
    // throw new IllegalStateException(
    // "student with id: " + studentId + "doesn't exist");
    // }
    // RoutesRepository.deleteById(studentId);
    // }

    // @Transactional
    // public void updateStudent(Long studentId,
    // String name,
    // String email) {
    // Routes Routes = RoutesRepository.findById(studentId)
    // .orElseThrow(() -> new IllegalStateException("studentID doesn't exist"));

    // if (name != null && name.length() > 0 && Routes.getName() != name) {
    // Routes.setName(name);
    // }
    // if (email != null && email.length() > 0 && Routes.getEmail() != email) {
    // Routes.setEmail(email);
    // }
    // }
}
