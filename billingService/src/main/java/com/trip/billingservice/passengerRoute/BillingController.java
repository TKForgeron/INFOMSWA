package com.trip.billingservice.passengerRoute;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class BillingController {

    private final BillingRepository billingRepository;
    private final BillingService billingService;


    public BillingController(BillingRepository billingRepository, BillingService billingService) {
        this.billingRepository = billingRepository;
        this.billingService = billingService;
    }

    @PostMapping(path = "build_routes")
    public void pullEventStores() throws URISyntaxException {
        // Post date of last update to the services broker
        LastUpdatedOn lastUpdate = new LastUpdatedOn();
        lastUpdate.setLastUpdatedOn(billingService.lastUpdatedOn());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LastUpdatedOn> httpEntity = new HttpEntity<>(lastUpdate, headers);
        RestTemplate restTemplate = new RestTemplate();
        URI requestUpdatesURI = new URI("http://localhost:7200/push_update");
        restTemplate.postForObject(requestUpdatesURI, httpEntity, LastUpdatedOn.class);
    }

    @PostMapping(path = "retrieve_eventstores")
    public void buildRoute(@RequestBody List<EventStore> eventStores) throws URISyntaxException {
        PassengerRoute route = billingService.buildRoutes(eventStores);
        System.out.println("Final route: " + route);
        billingRepository.save(route);
    }



}
