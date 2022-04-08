package com.trip.routesApplication.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/route/")
public class RoutesController {

    private final RoutesService RoutesService;

    @Autowired
    public RoutesController(RoutesService RoutesService) {
        this.RoutesService = RoutesService;
    }

    @GetMapping(path = "{stationA}/{stationB}")
    public Route getRouteDetails(@PathVariable("stationA") String stationA,
            @PathVariable("stationB") String stationB) {
        return RoutesService.findRouteByStations(stationA, stationB);
    }

    @GetMapping(path = "all")
    public List<Route> getAllRoutes() {
        return RoutesService.getRoutes();
    }

    @PostMapping(path = "add")
    public Route addRoute(@RequestBody Route route) {
        return RoutesService.addRoute(route);
    }

    @PutMapping(path = "update/{routeId}")
    public Route updateRoute(@PathVariable("routeId") Long routeIdToBeUpdated, @RequestBody Route routeToBe) {
        return RoutesService.updateRoute(routeIdToBeUpdated, routeToBe);
    }

    @DeleteMapping(path = "delete/{routeId}")
    public Route deleteRoute(@PathVariable("routeId") Long routeId, @RequestBody Route iKnowWhatRouteIAmDeleting) {
        return RoutesService.deleteRoute(routeId, iKnowWhatRouteIAmDeleting);
    }

}
