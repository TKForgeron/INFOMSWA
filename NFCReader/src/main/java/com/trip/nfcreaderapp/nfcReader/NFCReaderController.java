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
    private final NFCReaderRepository nfcReaderRepository;

    @Autowired
    public NFCReaderController(NFCReaderService NFCReaderService, NFCReaderRepository nfcReaderRepository) {
        this.NFCReaderService = NFCReaderService;
        this.nfcReaderRepository = nfcReaderRepository;
    }

    // ONLY FOR TESTING
    @GetMapping(path = "api/v1/nfcreader")
    public List<BankCard> getAccounts() {
        return NFCReaderService.getAccounts();
    }

    @PostMapping(path = "api/v1/nfcreader/eventstore")
    public void validateBankCard(@RequestBody BankCard BankCard) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Long uuid = NFCReaderService.validateBankCard(BankCard);
        Date date = new Date();
        String location = "utrecht";
        Integer tycoon = 1;

        EventStore utrecht = new EventStore(
                uuid,
                date,
                location,
                tycoon
        );

        URI uri = new URI("http://localhost:9100/eventstore/add");
        HttpEntity<EventStore> httpEntity = new HttpEntity<>(utrecht, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, EventStore.class);
    }

    @PostMapping(path = "nfcreader/bankcard/add")
    public void registerBankCard(@RequestBody BankCard bankCard) {
        NFCReaderService.registerNewBankCard(bankCard);
    }

    @PutMapping(path = "api/v1/nfcreader/bankcard/update/{uuid}")
    public void updateBankCard(@RequestBody BankCard bankCard) {
        nfcReaderRepository.save(bankCard);
    }

}
