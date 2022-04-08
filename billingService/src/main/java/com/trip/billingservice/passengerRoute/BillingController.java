package com.trip.billingservice.passengerRoute;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class BillingController {

    private final BillingRepository billingRepository;
    private final BillingService billingService;

    public BillingController(BillingRepository billingRepository, BillingService billingService) {
        this.billingRepository = billingRepository;
        this.billingService = billingService;
    }

    @PostMapping(path = "build_routes")
    public void processPayment() throws URISyntaxException {
        // Post date of last update to the services broker
        LastUpdatedOn lastUpdate = new LastUpdatedOn();
        lastUpdate.setLastUpdatedOn(billingService.lastUpdatedOn());
        System.out.println(lastUpdate);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LastUpdatedOn> httpEntity = new HttpEntity<>(lastUpdate, headers);
        RestTemplate restTemplate = new RestTemplate();

        // Set URI's
        URI requestUpdatesURI = new URI("http://localhost:7200/push_update");
        //URI uri = new URI("http://localhost:9100/account/pull");

        // Post Objects
        restTemplate.postForObject(requestUpdatesURI, httpEntity, LastUpdatedOn.class);



    }

}
