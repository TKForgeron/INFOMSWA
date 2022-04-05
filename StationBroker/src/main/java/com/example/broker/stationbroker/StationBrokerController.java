package com.example.broker.stationbroker;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping
public class StationBrokerController {

    private final StationBrokerService StationBrokerService;

    public StationBrokerController(com.example.broker.stationbroker.StationBrokerService StationBrokerService) {
        this.StationBrokerService = StationBrokerService;
    }

    @PostMapping(path="eventstore/**")
    public EventStore addEventStore(@RequestBody EventStore eventStore) {

        return eventStore;
    }

    @PostMapping(path="account/add")
    public void registerBankCard(@RequestBody BankCard bankCard) throws URISyntaxException {
        // Register account
        registerAccountBroker(bankCard);

        // Set headers and location
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String location = "utrecht";

        // Push newly added bankcard to AccountDB located on the NFC readers
        URI uri_NFCReader = new URI(String.format("http://localhost:8080/bankcard/add", location));
        BankCard newBankCard = new BankCard();
        newBankCard.setNfcId(bankCard.getNfcId());
        newBankCard.setUuid(bankCard.getUuid());
        newBankCard.setExpiryDate(bankCard.getExpiryDate());

        HttpEntity<BankCard> httpEntity = new HttpEntity<>(newBankCard, headers);
        RestTemplate restTemplate = new RestTemplate();
        BankCard bankCard1 = restTemplate.postForObject(uri_NFCReader, httpEntity, BankCard.class);

    }

    public void registerAccountBroker(BankCard bankCard){
        // Store account on DB of local broker
        Account newAccount = new Account();
        Date now = new Date();
        newAccount.setNfcId(bankCard.getNfcId());
        newAccount.setUuid(bankCard.getUuid());
        newAccount.setExpiryDate(bankCard.getExpiryDate());
        newAccount.setUpdatedOn(now);
        newAccount.setIban(bankCard.getIban());

        StationBrokerService.registerAccount(newAccount);
    }

    @PostMapping(path = "account/pull")
    public void pushNewAccounts(@RequestBody Optional<Date> date) throws URISyntaxException {
        // Set headers and location
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Push newly added bankcard to AccountDB located on the AccountService
        URI uri = new URI(String.format("http://localhost:7100/bankcard/add"));

//        HttpEntity<Account> httpEntity = new HttpEntity<>(newAccount, headers);
//        RestTemplate restTemplate = new RestTemplate();
//        Account addNewAccount = restTemplate.postForObject(uri, httpEntity, Account.class);

    }


}