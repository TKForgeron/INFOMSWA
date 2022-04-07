package com.example.broker.stationbroker;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping
public class StationBrokerController {

    private final StationBrokerService StationBrokerService;
    private final AccountRepository accountRepository;

    public StationBrokerController(com.example.broker.stationbroker.StationBrokerService StationBrokerService, AccountRepository accountRepository) {
        this.StationBrokerService = StationBrokerService;
        this.accountRepository = accountRepository;
    }

    @GetMapping(path = "accounts")
    public List<Account> printAccounts() {
        return StationBrokerService.getAccounts();
    }

    @PostMapping(path = "eventstore/**")
    public EventStore addEventStore(@RequestBody EventStore eventStore) {

        return eventStore;
    }

    @PostMapping(path = "account/add")
    public void registerBankCard(@RequestBody Account account) throws URISyntaxException {
        // Register account
        registerAccountBroker(account);

        // Set headers and location
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Push newly added bankcard to AccountDB located on the NFC readers
        URI uri = new URI("http://localhost:8080/nfcreader/bankcard/add");
        BankCard newBankCard = new BankCard(
                account.getUuid(),
                account.getExpiryDate(),
                account.getNfcId());

        HttpEntity<BankCard> httpEntity = new HttpEntity<>(newBankCard, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, BankCard.class);

    }

    public void registerAccountBroker(Account account) {
        // Store account on DB of local broker
        Date now = new Date();
        Account newAccount = new Account(
                account.getUuid(),
                account.getExpiryDate(),
                account.getNfcId(),
                account.getIban(),
                account.getDeleted(),
                account.getCreatedAt(),
                now);

        StationBrokerService.registerAccount(newAccount);
    }

    @PutMapping(path = "account/update/{uuid}")
    public void updateAccount(@PathVariable Long uuid, @RequestBody BankCard bankCard) throws URISyntaxException {

        Account account = accountRepository.findAccountByUuid(uuid).orElse(null);

        if (bankCard.getIban() != null) {
            account.setIban(bankCard.getIban());
        }

        if (bankCard.getExpiryDate() != null) {
            account.setExpiryDate(bankCard.getExpiryDate());
        }

        if (bankCard.getNfcId() != null){
            account.setNfcId(bankCard.getNfcId());
        }

        Date now = new Date();
        assert account != null;
        account.setUpdatedOn(now);
        accountRepository.save(account);
        updateBankCard(account);
    }

    public void updateBankCard(Account account) throws URISyntaxException {
        // Push newly updated bankcard to AccountDB located on the NFC readers
        Long uuid = account.getUuid();
        BankCard newBankCard = new BankCard(
                uuid,
                account.getExpiryDate(),
                account.getNfcId());

        URI uri = new URI(String.format("http://localhost:8080/api/v1/nfcreader/bankcard/update/%s", uuid));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri, newBankCard);
    }

    @PutMapping(path = "account/delete/{uuid}")
    public void deleteAccount(@PathVariable Long uuid, @RequestBody BankCard bankCard) throws URISyntaxException {
        Account account = accountRepository.findAccountByUuid(uuid).orElse(null);
        account.setDeleted(Boolean.TRUE);

        Date now = new Date();
        assert account != null;
        account.setUpdatedOn(now);
        accountRepository.save(account);

        URI uri = new URI(String.format("http://localhost:8080/api/v1/nfcreader/bankcard/delete/%s", uuid));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(String.valueOf(uri), account);
    }

    @PostMapping(path="request_update")
    public void getAccounts() throws URISyntaxException {
        // Post date of last update to local broker
        LastUpdatedOn lastUpdate = new LastUpdatedOn();
        lastUpdate.setLastUpdatedOn(StationBrokerService.lastUpdatedOn());
        System.out.println(lastUpdate);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI("http://localhost:7100/account/pull");
        HttpEntity<LastUpdatedOn> httpEntity = new HttpEntity<>(lastUpdate, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, httpEntity, LastUpdatedOn.class);

    }

    @PostMapping(path = "retrieve_update")
    public void retrieveUpdate(@RequestBody List<Account> accounts) {
        accountRepository.saveAll(accounts);
    }

}