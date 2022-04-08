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

    public List<Integer> getAccountSubscriptionsByNfcId(Integer nfcId) {
        Optional<Account> acc = AccountRepository.findAccountByNfcId(nfcId);
        if (!acc.isPresent()) {
            throw new IllegalStateException("User does not exist");
        }

        List<Integer> subs = acc.get().getSubscriptionIds();

        return subs;
    }

    public List<Integer> getAccountSubscriptionsByUuid(Long uuid) {
        Optional<Account> acc = AccountRepository.findById(uuid);
        if (!acc.isPresent()) {
            throw new IllegalStateException("User does not exist");
        }

        List<Integer> subs = acc.get().getSubscriptionIds();

        return subs;
    }

    public List<Account> getNewAccounts(Date lastUpdatedOn) {
        Optional<List<Account>> optionalAccountList = AccountRepository
                .findAccountsByUpdatedOnAfter(lastUpdatedOn);
        return optionalAccountList.orElse(null);
    }

    public void registerAccount(Account account) {
        Optional<Account> accountOptional = AccountRepository
                .findAccountByUuid(account.getUuid());
        if (accountOptional.isPresent()) {
            throw new IllegalStateException("User already exists");
        }
        AccountRepository.save(account);
    }
}
