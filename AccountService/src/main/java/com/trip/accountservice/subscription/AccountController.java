package com.trip.accountservice.subscription;

import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
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

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping(path = "accounts")
    public List<Account> printAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping(path = "account/add")
    public void registerBankCard(@RequestBody Account account) {
        accountService.registerAccount(account);
    }

    @PostMapping(path = "account/pull")
    public void pushNewAccounts(@RequestBody LastUpdatedOn lastUpdatedOn) throws URISyntaxException {
        List<Account> accounts = accountService.getNewAccounts(lastUpdatedOn.getLastUpdatedOn());

        // Set headers and location
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Push newly added bankcard to AccountDB located on the AccountService
        URI uri = new URI(String.format("http://localhost:9000/retrieve_update"));

        HttpEntity<List<Account>> httpEntity = new HttpEntity<>(accounts, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, Account.class);
    }
}