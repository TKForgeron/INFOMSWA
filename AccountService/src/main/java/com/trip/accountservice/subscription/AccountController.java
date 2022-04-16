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
import java.util.Optional;

@RestController
public class AccountController {

    private final AccountService AccountService;

    @Autowired
    public AccountController(AccountService AccountService) {
        this.AccountService = AccountService;
    }

    @GetMapping(path = "accounts")
    public List<Account> printAccounts() {
        return AccountService.getAccounts();
    }

    @GetMapping(path = "account/{uuid}/iban")
    public String getAccountIban(@PathVariable("uuid") Long uuid) {
        return AccountService.getAccountIban(uuid);
    }

    @PostMapping(path = "account/add")
    public void registerBankCard(@RequestBody Account account) {
        AccountService.registerAccount(account);
    }

    @GetMapping(path = "account/{nfcId}/get/subscriptions")
    public List<Integer> getUserSubscriptionsByNfcId(@PathVariable("nfcId") Integer nfcId) {
        return AccountService.getAccountSubscriptionsByNfcId(nfcId);
    }

    @GetMapping(path = "from_billing/account/{uuid}/get/subscriptions")
    public List<Integer> getUserSubscriptionsByUuid(@PathVariable("uuid") Long uuid) {
        return AccountService.getAccountSubscriptionsByUuid(uuid);
    }

    @PostMapping(path = "account/pull")
    public void pushNewAccounts(@RequestBody LastUpdatedOn lastUpdatedOn) throws URISyntaxException {
        List<Account> accounts = AccountService.getNewAccounts(lastUpdatedOn.getLastUpdatedOn());

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