package com.trip.serviceterminal.bankcard;

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
@RequestMapping(path = "api/v1/serviceterminal/bankcard/")
public class BankCardController {

    private Boolean bankCardIsExpired(BankCard BankCard) {
        return !LocalDate.now().isAfter(BankCard.expiryDate);
    }

    // private final BankCardService BankCardService;

    // @Autowired
    // public BankCardController(BankCardService BankCardService) {
    // this.BankCardService = BankCardService;
    // }

    @DeleteMapping(path = "{nfcIdPath}")
    public void deleteBankCard(
            @PathVariable("nfcIdPath") Integer nfcIdPath,
            // bank card data must be given for auth
            @RequestBody(required = true) Integer nfcId,
            @RequestBody(required = true) LocalDate expiryDate,
            @RequestBody(required = true) String iban) {
        BankCard bankCard = new BankCard(nfcId, expiryDate, iban);
        if (nfcId != nfcIdPath) {
            if (bankCardIsExpired(bankCard)) {
                throw new IllegalStateException("Bank card cannot be deleted, you are unauthorized!");
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(String.format("http://localhost:9000/account/delete/%s", (String) nfcIdPath));

        HttpEntity<BankCard> httpEntity = new HttpEntity<>(bankCard, headers);

        RestTemplate restTemplate = new RestTemplate();
        bankCard = restTemplate.postForObject(uri, httpEntity, BankCard.class);

        System.out.println(bankCard.toString(), "\n ...deleted");
    }

    @PutMapping(path = "{nfcIdPath}")
    public void updateBankCard(
            // ID to be updated
            @PathVariable("nfcIdPath") Integer nfcIdPath,
            // data of that ID to be updated
            @RequestBody(required = false) Integer nfcId, // If one registers a new NFC-card, replacing the old
            // (e.g. expired) one
            @RequestBody(required = false) LocalDate expiryDate,
            @RequestBody(required = false) String iban) {
        BankCard bankCard = new BankCard(nfcId, expiryDate, iban);
        if (bankCardIsExpired(bankCard)) {
            throw new IllegalStateException("Bank card cannot be registered, it is expired!");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(String.format("http://localhost:9000/account/%s", nfcIdPath));

        HttpEntity<BankCard> httpEntity = new HttpEntity<>(bankCard, headers);

        RestTemplate restTemplate = new RestTemplate();
        bankCard = restTemplate.postForObject(uri, httpEntity, BankCard.class);

        System.out.println(bankCard.toString(), "\n ...updated");
    }

    @PostMapping(path = "add")
    public void registerNewBankCard(@RequestBody BankCard BankCard) throws URISyntaxException {

        if (bankCardIsExpired(BankCard)) {
            throw new IllegalStateException("Bank card cannot be registered, it is expired!");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI("http://localhost:9000/account/add");

        HttpEntity<BankCard> httpEntity = new HttpEntity<>(BankCard, headers);

        RestTemplate restTemplate = new RestTemplate();
        BankCard bankCard = restTemplate.postForObject(uri, httpEntity, BankCard.class);

        System.out.println(bankCard.toString(), "\n ...added");

    }
}
