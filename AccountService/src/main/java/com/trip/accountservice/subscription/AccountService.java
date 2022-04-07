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

    public List<Account> getNewAccounts(Date lastUpdatedOn) {
        Optional<List<Account>> optionalAccountList= AccountRepository
                .findAccountsByUpdatedOnAfter(lastUpdatedOn);
        return optionalAccountList.orElse(null);
    }

    public void registerAccount(Account account) {
        Optional<Account> accountOptional= AccountRepository
                .findAccountByUuid(account.getUuid());
        if (accountOptional.isPresent()) {
            throw new IllegalStateException("User already exists");
        }
        AccountRepository.save(account);
    }
}
