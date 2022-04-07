package com.example.broker.localaccountbroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StationBrokerService {

    private final AccountRepository AccountRepository;

    @Autowired
    public StationBrokerService(com.example.broker.localaccountbroker.AccountRepository AccountRepository) {
        this.AccountRepository = AccountRepository;
    }

    public List<Account> getAccounts() {
        return AccountRepository.findAll();
    }

    public void registerAccount(Account account) {
        Optional<Account> accountOptional= AccountRepository
                .findAccountByUuid(account.getUuid());
        if (accountOptional.isPresent()) {
            throw new IllegalStateException("User already exists");
        }

        AccountRepository.save(account);
    }

    public Date lastUpdatedOn(){
        Optional<Date> lastUpdated = AccountRepository.findTopByUpdatedOn();
        return lastUpdated.orElse(null);
    }

}
