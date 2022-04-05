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

    // ONLY FOR TESTING
    @GetMapping(path = "api/v1/nfcreader")
    public List<BankCard> getAccounts() {
        return NFCReaderService.getAccounts();
    }

    @PostMapping(path = "api/v1/nfcreader/eventstore")
    public void validateBankCard(@RequestBody BankCard BankCard) throws URISyntaxException {
        Long uuid = NFCReaderService.validateBankCard(BankCard);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String location = "utrecht";
        URI uri = new URI(String.format("http://localhost:9000/eventstore/%s", location));

        EventStore utrecht = new EventStore();
        Date date = Calendar.getInstance().getTime();
        utrecht.setDate(date);
        utrecht.setLocation(location);
        utrecht.setUUID(uuid);

        HttpEntity<EventStore> httpEntity = new HttpEntity<>(utrecht, headers);

        RestTemplate restTemplate = new RestTemplate();
        EventStore eventStore = restTemplate.postForObject(uri, httpEntity, EventStore.class);

        System.out.println(eventStore.toString());

    }

    @PostMapping(path = "nfcreader/bankcard/add")
    public void registerBankCard(@RequestBody BankCard bankCard) {
        NFCReaderService.registerNewBankCard(bankCard);
    }

    @PutMapping(path = "api/v1/nfcreader/bankcard/update/{nfcId}")
    public void updateBankCard(@RequestBody BankCard bankCard, @PathVariable Integer nfcId) {
        NFCReaderService.updateBankCard(nfcId, bankCard);
    }

}
