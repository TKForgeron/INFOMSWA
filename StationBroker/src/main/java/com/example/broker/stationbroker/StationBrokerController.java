package com.example.broker.stationbroker;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping
public class StationBrokerController {

    private final StationBrokerService StationBrokerService;

    public StationBrokerController(com.example.broker.stationbroker.StationBrokerService StationBrokerService) {
        this.StationBrokerService = StationBrokerService;
    }

    @GetMapping(path = "accounts")
    public List<Account> printAccounts() {
        return StationBrokerService.getAccounts();
    }

    @PostMapping(path = "eventstore/**")
    public EventStore addEventStore(@RequestBody EventStore eventStore) {

        return eventStore;
    }

    @PostMapping(path = "account/add")
    public void registerBankCard(@RequestBody BankCard bankCard) throws URISyntaxException {
        // Register account
        registerAccountBroker(bankCard);

        // Set headers and location
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String location = "utrecht";

        // Push newly added bankcard to AccountDB located on the NFC readers
        URI uri = new URI("http://localhost:8080/nfcreader/bankcard/add");
        BankCard newBankCard = new BankCard(
                bankCard.getUuid(),
                bankCard.getExpiryDate(),
                bankCard.getNfcId());

        HttpEntity<BankCard> httpEntity = new HttpEntity<>(newBankCard, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, BankCard.class);

    }

    @PutMapping(path = "account/update/{nfcId}")
    public void registerBankCard(@PathVariable Integer nfcId, @RequestBody BankCard bankCard)
            throws URISyntaxException {

        // Set headers and location
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String location = "utrecht";

        // Push newly added bankcard to AccountDB located on the NFC readers
        URI uri_NFCReader = new URI(String.format("http://localhost:8080/nfcreader/bankcard/update", location));
        BankCard newBankCard = new BankCard(
                bankCard.getUuid(),
                bankCard.getExpiryDate(),
                bankCard.getNfcId());

        HttpEntity<BankCard> httpEntity = new HttpEntity<>(newBankCard, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri_NFCReader, httpEntity, BankCard.class);

    }

    public void registerAccountBroker(BankCard bankCard) {
        // Store account on DB of local broker
        Date now = new Date();
        Account newAccount = new Account(
                bankCard.getUuid(),
                bankCard.getExpiryDate(),
                bankCard.getNfcId(),
                bankCard.getIban(),
                bankCard.getCreatedAt(),
                now);

        StationBrokerService.registerAccount(newAccount);
    }

    @PostMapping(path = "account/pull")
    public void pushNewAccounts(@RequestBody LastUpdatedOn lastUpdatedOn) throws URISyntaxException {
        List<Account> accounts = StationBrokerService.getNewAccounts(lastUpdatedOn.getLastUpdatedOn());

        // Set headers and location
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Push newly added bankcard to AccountDB located on the AccountService
        URI uri = new URI(String.format("http://localhost:7100/retrieve_update"));

        HttpEntity<List<Account>> httpEntity = new HttpEntity<>(accounts, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, Account.class);
    }

}