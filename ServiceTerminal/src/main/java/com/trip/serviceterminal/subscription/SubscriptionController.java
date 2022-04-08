package com.trip.serviceterminal.subscription;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/serviceterminal/subscription/")
public class SubscriptionController {
    private Subscription[] possibleSubscriptions;

    @DeleteMapping(path = "delete/{subId}/for_user/{nfcIdPath}")
    public void deleteSubscription(
            @PathVariable("subId") Integer subId,
            @PathVariable("nfcIdPath") Integer nfcIdPath,
            @RequestBody Long id) throws URISyntaxException {

        Subscription subscription = new Subscription();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(
                String.format("http://localhost:9000/account/%1$s/delete/subscription/%2$s", nfcIdPath, subId));

        HttpEntity<Subscription> httpEntity = new HttpEntity<>(subscription, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, Subscription.class);

        // System.out.println(subscription.toString(), "\n ...deleted");
    }

    @PostMapping(path = "add/{subId}/for_user/{nfcIdPath}")
    public void addNewSubscription(
            @PathVariable("subId") Integer subId,
            @PathVariable("nfcIdPath") Integer nfcIdPath,
            @RequestBody(required = true) Subscription subscription) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(String.format("http://localhost:9000/account/%1$s/add/subscription/%2$s", nfcIdPath, subId));

        HttpEntity<Subscription> httpEntity = new HttpEntity<>(subscription, headers);

        RestTemplate restTemplate = new RestTemplate();
        subscription = restTemplate.postForObject(uri, httpEntity, Subscription.class);

        // System.out.println(subscription.toString(), "\n ...added");

    }

    @GetMapping(path = "all")
    public Subscription[] fetchSubscriptions() throws URISyntaxException {

        URI uri = new URI("http://localhost:4900/subscription/all");
        RestTemplate restTemplate = new RestTemplate();
        Subscription[] sub = restTemplate.getForObject(uri, Subscription[].class);
        // System.out.println(subscription.toString(), "\n ...added");

    }

    @GetMapping(path = "for_user/{nfcId}")
    public int[] fetchUserSubscriptions(@PathVariable("nfcId") Integer nfcId) throws URISyntaxException {
        URI uri = new URI(String.format("http://localhost:9000/account/%1$s/get/subscriptions", nfcId));
        RestTemplate restTemplate = new RestTemplate();
        int[] subIds = restTemplate.getForObject(uri, int[].class);

        return;
        // System.out.println(subscription.toString(), "\n ...added");

    }
}
