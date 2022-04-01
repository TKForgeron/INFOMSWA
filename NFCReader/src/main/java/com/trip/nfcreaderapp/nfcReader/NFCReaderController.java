package com.trip.nfcreaderapp.nfcReader;

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

@RestController
public class NFCReaderController {

    private final NFCReaderService NFCReaderService;

    @Autowired
    public NFCReaderController(NFCReaderService NFCReaderService) {
        this.NFCReaderService = NFCReaderService;
    }

    @GetMapping(path="api/v1/nfcreader")
    public List<BankCard> getAccounts() {
        return NFCReaderService.getAccounts();
    }

    @PostMapping(path="api/v1/eventstore")
    public void validateBankCard(@RequestBody BankCard BankCard) throws URISyntaxException {
        Long uuid = NFCReaderService.validateBankCard(BankCard);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String location = "utrecht";
        URI uri = new URI("http://localhost:8080/api/v1/eventstore/utrecht");

        EventStore utrecht = new EventStore();
        Date date = Calendar.getInstance().getTime();
        utrecht.setDate(date);
        utrecht.setLocation(location);
        utrecht.setUUID(uuid);

        HttpEntity<EventStore> httpEntity = new HttpEntity<>(utrecht, headers);
        System.out.println("Test");
        RestTemplate restTemplate = new RestTemplate();
        EventStore eventStore = restTemplate.postForObject(uri, httpEntity, EventStore.class);

    }

    @PostMapping(path = "api/v1/eventstore/**")
    public EventStore addEventStore(@RequestBody EventStore eventStore) {

        return eventStore;
    }

}
