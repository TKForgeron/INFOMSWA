package com.trip.accountservice.subscription;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository AccountRepository;

    public AccountService(AccountRepository AccountRepository) {
        this.AccountRepository = AccountRepository;
    }

    public List<Account> getAccounts() {
        return AccountRepository.findAll();
    }

    public Date lastUpdatedOn(){
        Optional<Date> lastUpdated = AccountRepository.findTopByUpdatedOn();
        return lastUpdated.orElse(null);
    }
}
