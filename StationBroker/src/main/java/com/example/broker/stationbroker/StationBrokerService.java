package com.example.broker.stationbroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StationBrokerService {

    private final BankCardRepository BankCardRepository;
    private final AccountRepository AccountRepository;
    private final LastDateInRepository LastDateInRepository;

    @Autowired
    public StationBrokerService(BankCardRepository BankCardRepository, com.example.broker.stationbroker.AccountRepository AccountRepository, com.example.broker.stationbroker.LastDateInRepository LastDateInRepository) {
        this.BankCardRepository = BankCardRepository;
        this.AccountRepository = AccountRepository;
        this.LastDateInRepository = LastDateInRepository;
    }

    public List<BankCard> getAccounts() {
        return BankCardRepository.findAll();
    }

    public Long validateBankCard(BankCard BankCard) {
        Optional<BankCard> bankCardOptional = BankCardRepository
                .findBankCardByNfcId(BankCard.getNfcId());
        BankCard bankCard;

        if (!bankCardOptional.isPresent()) {
            throw new IllegalStateException("Bank card does not exist");
        } else {
            bankCard = bankCardOptional.get();
        }

        Date today = Calendar.getInstance().getTime();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date expiryDate = Date.from(BankCard.getExpiryDate().atStartOfDay(defaultZoneId).toInstant());
        if (today.after(expiryDate)) {
            System.out.println("Your bank card has expired, please go to the service terminal.");
            throw new IllegalStateException("Bank card expired");
        }

        return bankCard.getUuid();
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
