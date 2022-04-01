package com.example.broker.stationbroker;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class StationBrokerController {

    @PostMapping(path="eventstore/**")
    public EventStore addEventStore(@RequestBody EventStore eventStore) {

        return eventStore;
    }

    @PostMapping(path="account/add")
    public BankCard addBankCard(@RequestBody BankCard bankCard) {

        return bankCard;
    }


}