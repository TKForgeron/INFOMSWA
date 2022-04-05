package com.trip.serviceterminal.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping(path = "api/v1/serviceterminal/subscription/")
public class SubscriptionController {

    @DeleteMapping(path = "delete/{nfcIdPath}")
    public void deleteSubscription(
            @PathVariable("nfcIdPath") Integer nfcIdPath,
            // bank card data must be given for auth
            @RequestBody(required = true) Long id) throws URISyntaxException {
        Subscription subscription = new Subscription(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(String.format("http://localhost:9000/account/delete/%s", nfcIdPath));

        HttpEntity<Subscription> httpEntity = new HttpEntity<>(Subscription, headers);

        RestTemplate restTemplate = new RestTemplate();
        Subscription = restTemplate.postForObject(uri, httpEntity, Subscription.class);

        // System.out.println(Subscription.toString(), "\n ...deleted");
    }

    @PutMapping(path = "update/{nfcIdPath}")
    public void updateSubscription(
            // ID to be updated
            @PathVariable("nfcIdPath") Integer nfcIdPath,
            // data of that ID to be updated
            @RequestBody(required = false) Integer nfcId, // If one registers a new NFC-card, replacing the old
            // (e.g. expired) one
            @RequestBody(required = false) LocalDate expiryDate,
            @RequestBody(required = false) String iban) throws URISyntaxException {
        Subscription Subscription = new Subscription(expiryDate, nfcId, iban);
        if (SubscriptionIsExpired(Subscription)) {
            throw new IllegalStateException("Bank card cannot be registered, it is expired!");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(String.format("http://localhost:9000/account/%s", nfcIdPath));

        HttpEntity<Subscription> httpEntity = new HttpEntity<>(Subscription, headers);

        RestTemplate restTemplate = new RestTemplate();
        Subscription = restTemplate.postForObject(uri, httpEntity, Subscription.class);

        // System.out.println(Subscription.toString(), "\n ...updated");
    }

    @PostMapping(path = "add")
    public void registerNewSubscription(@RequestBody Subscription Subscription) throws URISyntaxException {

        if (SubscriptionIsExpired(Subscription)) {
            throw new IllegalStateException("Bank card cannot be registered, it is expired!");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI("http://localhost:9000/account/add");

        HttpEntity<Subscription> httpEntity = new HttpEntity<>(Subscription, headers);

        RestTemplate restTemplate = new RestTemplate();
        Subscription Subscription = restTemplate.postForObject(uri, httpEntity, Subscription.class);

        // System.out.println(Subscription.toString(), "\n ...added");

    }
}
