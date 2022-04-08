package com.trip.serviceterminal.subscription;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping(path = "api/v1/serviceterminal/subscription/")
public class SubscriptionController {

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
        URI uri = new URI(String.format("http://localhost:7100/account/%1$s/add/subscription/%2$s", nfcIdPath, subId));

        HttpEntity<Subscription> httpEntity = new HttpEntity<>(subscription, headers);

        RestTemplate restTemplate = new RestTemplate();
        subscription = restTemplate.postForObject(uri, httpEntity, Subscription.class);

        // System.out.println(subscription.toString(), "\n ...added");

    }

    // private Subscription[] allPossibleSubscriptions;

    @GetMapping(path = "all")
    public Subscription[] fetchSubscriptions() throws URISyntaxException {

        URI uri = new URI("http://localhost:4900/subscription/all");
        RestTemplate restTemplate = new RestTemplate();
        Subscription[] allPossibleSubscriptions = restTemplate.getForObject(uri, Subscription[].class);
        return allPossibleSubscriptions;
        // System.out.println(subscription.toString(), "\n ...added");

    }

    @GetMapping(path = "all/for_user/{nfcId}")
    public List<Subscription> fetchUserSubscriptions(@PathVariable("nfcId") Integer nfcId) throws URISyntaxException {
        URI uri = new URI(String.format("http://localhost:7100/account/%1$s/get/subscriptions", nfcId));
        RestTemplate restTemplate = new RestTemplate();
        Long[] subIds = restTemplate.getForObject(uri, Long[].class);
        Subscription[] allPossibleSubscriptions = fetchSubscriptions();

        List<Subscription> userSubs = new ArrayList<>();
        for (Subscription sub : allPossibleSubscriptions) {

            if (Arrays.asList(subIds).contains(sub.getId())) {
                userSubs.add(sub);
            }
        }
        return userSubs;
        // System.out.println(subscription.toString(), "\n ...added");

    }
}
