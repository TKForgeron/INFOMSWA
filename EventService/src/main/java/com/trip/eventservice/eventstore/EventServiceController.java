package com.trip.eventservice.eventstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class EventServiceController {

    private final EventServiceService eventServiceService;
    private final EventServiceRepository eventServiceRepository;

    @Autowired
    public EventServiceController(EventServiceService eventServiceService, EventServiceRepository eventServiceRepository) {
        this.eventServiceService = eventServiceService;
        this.eventServiceRepository = eventServiceRepository;
    }

    @GetMapping(path = "eventstores")
    public List<EventStore> getEventStores() {
        return eventServiceService.getEventStores();
    }

    @PostMapping(path="request_update")
    public void getNewEventStores() throws URISyntaxException {
        // Post date of last update to local broker
        LastUpdatedOn lastUpdate = new LastUpdatedOn();
        lastUpdate.setLastUpdatedOn(eventServiceService.lastUpdatedOn());
        System.out.println(lastUpdate);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI("http://localhost:9100/account/pull");
        HttpEntity<LastUpdatedOn> httpEntity = new HttpEntity<>(lastUpdate, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, LastUpdatedOn.class);
    }

    @PostMapping(path = "retrieve_update")
    public void retrieveUpdate(@RequestBody List<EventStore> eventStores) {
        eventServiceRepository.saveAll(eventStores);
    }

    @PostMapping(path = "push_update")
    public void pushUpdate(@RequestBody LastUpdatedOn lastUpdatedOn) throws URISyntaxException {
        // Pull new EventStores
        getNewEventStores();
        List<EventStore> eventStores = eventServiceService.getNewEventStores(lastUpdatedOn.getLastUpdatedOn());

        // Set headers and location
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Push newly added accounts to billingService
        URI uri = new URI(String.format("http://localhost:7300/retrieve_eventstores"));

        HttpEntity<List<EventStore>> httpEntity = new HttpEntity<>(eventStores, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, EventStore.class);
    }


}