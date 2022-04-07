package com.trip.serviceterminal.subscription;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Flow;

@RestController
@RequestMapping(path = "api/v1/serviceterminal/subscription/")
public class SubscriptionController {

    @DeleteMapping(path = "delete/{nfcIdPath}")
    public void deleteSubscription(
            @PathVariable("nfcIdPath") Integer nfcIdPath,
            @RequestBody Long id) throws URISyntaxException {

        Subscription subscription = new Subscription();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(String.format("http://localhost:9000/account/subscription/delete/%s", nfcIdPath));

        HttpEntity<Subscription> httpEntity = new HttpEntity<>(subscription, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, Flow.Subscription.class);

        // System.out.println(subscription.toString(), "\n ...deleted");
    }

    @PostMapping(path = "add/{nfcIdPath}")
    public void addNewSubscription(
            @PathVariable("nfcIdPath") Integer nfcIdPath,
            @RequestBody(required = true) Flow.Subscription subscription) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(String.format("http://localhost:9000/account/subscription/add/%s", nfcIdPath));

        HttpEntity<Flow.Subscription> httpEntity = new HttpEntity<>(subscription, headers);

        RestTemplate restTemplate = new RestTemplate();
        subscription = restTemplate.postForObject(uri, httpEntity, Flow.Subscription.class);

        // System.out.println(subscription.toString(), "\n ...added");

    }

    @GetMapping(path = "all")
    public void fetchSubscriptions() throws URISyntaxException {

        URI uri = new URI("http://localhost:4900/subscription/all");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(uri, Flow.Subscription[].class);
        // System.out.println(subscription.toString(), "\n ...added");

    }
}
