package com.example.broker.stationbroker;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="http://localhost:8080/api/v1")
public class StationBrokerController {

    @PostMapping(path="/eventstore/**")
    public EventStore addEventStore(@RequestBody EventStore eventStore) {

        return eventStore;
    }
}

