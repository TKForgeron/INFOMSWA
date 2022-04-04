package com.example.broker.stationbroker;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping
public class StationBrokerController {

    @PostMapping(path="eventstore/**")
    public EventStore addEventStore(@RequestBody EventStore eventStore) {

        return eventStore;
    }

    @PostMapping(path="account/add")
    public BankCard registerBankCard(@RequestBody BankCard bankCard) throws URISyntaxException {
        // Push newly added bankcard to AccountDB located on the NFC readers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String location = "utrecht";
        URI uri = new URI(String.format("http://localhost:8080/bankcard/add", location));

        BankCard newBankCard = new BankCard();
        newBankCard.setNfcId(bankCard.getNfcId());
        newBankCard.setUuid(bankCard.getUuid());
        newBankCard.setExpiryDate(bankCard.getExpiryDate());

        HttpEntity<BankCard> httpEntity = new HttpEntity<>(newBankCard, headers);

        RestTemplate restTemplate = new RestTemplate();
        EventStore eventStore = restTemplate.postForObject(uri, httpEntity, EventStore.class);

        return bankCard;
    }


}