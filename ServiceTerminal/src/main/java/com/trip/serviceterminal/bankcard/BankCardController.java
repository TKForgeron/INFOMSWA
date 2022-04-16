package com.trip.serviceterminal.bankcard;

import com.trip.serviceterminal.account.Account;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Arrays;
import java.time.LocalDate;

@RestController
@RequestMapping(path = "serviceterminal/bankcard/")
public class BankCardController {

    @PostMapping(path = "add")
    public void registerNewBankCard(@RequestBody BankCard bankCard) throws URISyntaxException {

        if (bankCard.isExpired()) {
            throw new IllegalStateException("Bank card cannot be registered, it is expired!");
        }

        // private Long uuid;
        // private LocalDate expiryDate;
        // private Integer nfcId;
        // private String iban;
        // private String subscriptionIds;
        // private Boolean deleted;
        // private Date createdAt;

        // Create new account for Account Service
        Date now = new Date();
        Account newAccount = new Account(
                bankCard.getUuid(),
                bankCard.getExpiryDate(),
                bankCard.getNfcId(),
                bankCard.getIban(),
                Arrays.asList(0),
                bankCard.isDeleted(),
                now,
                now);
        System.out.println(newAccount);
        System.out.println(newAccount);
        System.out.println(newAccount);
        System.out.println(newAccount);
        System.out.println(newAccount);
        System.out.println(newAccount);
        System.out.println(newAccount);
        System.out.println(newAccount);

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        // Sent new account to accountService
        URI uri_accountService = new URI("http://localhost:7100/account/add");
        HttpEntity<Account> httpEntity_accountService = new HttpEntity<>(newAccount, headers);
        restTemplate.postForObject(uri_accountService, httpEntity_accountService, Account.class);

        // Sent new account to Local Broker
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri_broker = new URI("http://localhost:9000/account/add");
        HttpEntity<Account> httpEntity = new HttpEntity<>(newAccount, headers);
        restTemplate.postForObject(uri_broker, httpEntity, Account.class);
    }

    @PutMapping(path = "update/{uuid}")
    public void updateBankCard(@RequestBody BankCard bankCard) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Long uuid = bankCard.getUuid();
        URI uri = new URI("http://localhost:9000/account/update");

        BankCard newBankCard = new BankCard(
                uuid,
                bankCard.getExpiryDate(),
                bankCard.getNfcId(),
                bankCard.getIban());

        HttpEntity<BankCard> httpEntity = new HttpEntity<>(newBankCard, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri, httpEntity);
    }

    @DeleteMapping(path = "delete/{nfcIdPath}")
    public void deleteBankCard(
            @PathVariable("nfcIdPath") Integer nfcIdPath,
            // bank card data must be given for auth
            @RequestBody(required = true) Integer nfcId,
            @RequestBody(required = true) LocalDate expiryDate,
            @RequestBody(required = true) String iban) throws URISyntaxException {
        BankCard bankCard = new BankCard(expiryDate, nfcId, iban);
        if (nfcId != nfcIdPath) {
            if (bankCard.isExpired()) {
                throw new IllegalStateException("Bank card cannot be deleted, you are unauthorized!");
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(String.format("http://localhost:9000/account/delete/%s", nfcIdPath));

        HttpEntity<BankCard> httpEntity = new HttpEntity<>(bankCard, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, BankCard.class);
    }
}
