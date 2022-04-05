package com.trip.accountservice.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }


    @GetMapping(path = "accounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }


    @PostMapping(path="request_update")
    public void getAccounts() throws URISyntaxException {
        // Post date of last update to local broker
        LastUpdatedOn lastUpdate = new LastUpdatedOn();
        lastUpdate.setLastUpdatedOn(accountService.lastUpdatedOn());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI("http://localhost:9000/account/pull");
        HttpEntity<LastUpdatedOn> httpEntity = new HttpEntity<>(lastUpdate, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, LastUpdatedOn.class);

    }


}