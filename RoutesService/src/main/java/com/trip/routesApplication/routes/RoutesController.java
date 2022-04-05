package com.trip.routesApplication.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/route")
public class RoutesController {

    private final RoutesService RoutesService;

    @Autowired
    public RoutesController(RoutesService RoutesService) {
        this.RoutesService = RoutesService;
    }

    @GetMapping(path = "/{stationA}/{stationB}")
    public Route getRouteDetails(@PathVariable("stationA") String stationA, @PathVariable("stationB") String stationB) {
        return RoutesService.findRouteByStations(stationA, stationB);
    }

    @GetMapping(path = "all")
    public List<Route> getAllRoutes() {
        return RoutesService.getRoutes();
    }

    // @PostMapping
    // public void registerNewStudent(@RequestBody Routes Routes) {
    // RoutesService.addNewStudent(Routes);
    // }

    // @DeleteMapping(path = "{studentId}")
    // public void deleteStudent(@PathVariable("studentId") Long studentId) {
    // RoutesService.deleteStudent(studentId);
    // }

    // @PutMapping(path = "{studentId}")
    // public void updateStudent(
    // @PathVariable("studentId") Long studentId,
    // @RequestParam(required = false) String name,
    // @RequestParam(required = false) String email) {
    // RoutesService.updateStudent(studentId, name, email);
    // }
}
