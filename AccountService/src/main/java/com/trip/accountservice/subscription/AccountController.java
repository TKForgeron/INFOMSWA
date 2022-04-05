package com.trip.accountservice.subscription;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AccountController {

    @PostMapping(path="eventstore/**")
    public Account addAccount(@RequestBody Account account) {

        return account;
    }


}