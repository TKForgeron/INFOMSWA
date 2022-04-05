package com.trip.accountservice.subscription;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping
public class AccountController {

    private final com.example.demo.student.AccountService AccountService;

    public AccountController(com.example.demo.student.AccountService AccountService) {
        this.AccountService = AccountService;
    }

    @GetMapping(path="eventstore/**")
    public Account getAccounts(@RequestBody Account account) throws URISyntaxException {
        Optional<Date> lastPull = AccountService.lastUpdatedOn();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI("http://localhost:9000/account/pull");
        HttpEntity<Optional<Date>> httpEntity = new HttpEntity<>(lastPull, headers);
        RestTemplate restTemplate = new RestTemplate();
        Optional<Date> newPull = restTemplate.getForObject(uri, httpEntity, Optional<Date>);

        return account;
    }


}