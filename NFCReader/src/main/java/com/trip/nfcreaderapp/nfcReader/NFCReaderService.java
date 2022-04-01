package com.trip.nfcreaderapp.nfcReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class NFCReaderService {

    private final NFCReaderRepository NFCReaderRepository;

    @Autowired
    public NFCReaderService(NFCReaderRepository NFCReaderRepository) {
        this.NFCReaderRepository = NFCReaderRepository;
    }

    public List<BankCard> getAccounts() {
        return NFCReaderRepository.findAll();
    }

    public Long validateBankCard(BankCard BankCard) {
        Optional<BankCard> bankCardOptional = NFCReaderRepository
                .findBankCardByNfcId(BankCard.getNfcId());
        BankCard bankCard;

        if (bankCardOptional.isPresent() == false) {
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