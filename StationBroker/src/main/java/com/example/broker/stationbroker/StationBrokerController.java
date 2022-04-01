package com.example.broker.stationbroker;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="eventstore/**")
public class StationBrokerController {

    @PostMapping
    public EventStore addEventStore(@RequestBody EventStore eventStore) {

        return eventStore;
    }
}