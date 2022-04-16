package com.example.broker.localaccountbroker;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping(path = "eventstore/add")
    public void registerBankCard(
            @RequestBody EventStore eventStore) {
        eventstoreBrokerService.registerEventStore(eventStore);
    }

    @PostMapping(path = "account/pull")
    public void pushEventStores(@RequestBody LastUpdatedOn lastUpdatedOn) throws URISyntaxException {
        List<EventStore> eventStores = eventstoreBrokerService.getNewEventStores(lastUpdatedOn.getLastUpdatedOn());

        // Set headers and location
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Push newly added bankcard to AccountDB located on the AccountService
        URI uri = new URI(String.format("http://localhost:7200/retrieve_update"));

        HttpEntity<List<EventStore>> httpEntity = new HttpEntity<>(eventStores, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, EventStore.class);
    }
}