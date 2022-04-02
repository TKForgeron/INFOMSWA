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

    private final AccountRepository AccountRepository;

    @Autowired
    public StationBrokerService(AccountRepository AccountRepository) {
        this.AccountRepository = AccountRepository;
    }

    public List<BankCard> getAccounts() {
        return AccountRepository.findAll();
    }

    public Long validateBankCard(BankCard BankCard) {
        Optional<BankCard> bankCardOptional = AccountRepository
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
}
