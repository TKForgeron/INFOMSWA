package com.example.broker.localaccountbroker;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class EventStoreBrokerController {

    private final EventStoreBrokerService eventstoreBrokerService;

    public EventStoreBrokerController(EventStoreBrokerService eventstoreBrokerService) {
        this.eventstoreBrokerService = eventstoreBrokerService;
    }

    // ONLY FOR TESTING
    @GetMapping(path = "eventstores")
    public List<EventStore> getEventStores() {
        return eventstoreBrokerService.getEventStores();
    }

    @PostMapping(path="eventstore/add")
    public void registerBankCard(
            @RequestBody EventStore eventStore) {
        eventstoreBrokerService.registerEventStore(eventStore);
    }
}