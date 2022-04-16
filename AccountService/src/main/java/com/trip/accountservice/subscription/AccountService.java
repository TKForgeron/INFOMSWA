package com.trip.accountservice.subscription;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository AccountRepository;

    @Autowired
    public AccountService(AccountRepository AccountRepository) {
        this.AccountRepository = AccountRepository;
    }

    public List<Account> getAccounts() {
        return AccountRepository.findAll();
    }

    public String getAccountIban(Long uuid) {
        Optional<Account> account = AccountRepository.findById(uuid);
        if (!account.isPresent()) {
            throw new IllegalStateException("User does not exist");
        }

        return account.get().getIban();
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

    @Transactional
    public Account addSubscriptionToUser(Integer nfcId, Long subId) {
        Account accountCurrent = AccountRepository.findAccountByNfcId(nfcId)
                .orElseThrow(() -> new IllegalStateException("nfcId does not exist!"));

        if (subId != null) {
            List<Integer> currentSubs = accountCurrent.getSubscriptionIds();
            currentSubs.add((int) (long) subId);
            accountCurrent.setSubscriptionIds(currentSubs);
        }

        return accountCurrent;
    }
}
