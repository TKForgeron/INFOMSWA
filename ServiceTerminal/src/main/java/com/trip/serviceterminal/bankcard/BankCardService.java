package com.trip.serviceterminal.bankcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BankCardService {

    private final BankCardRepository BankCardRepository;

    @Autowired
    public BankCardService(BankCardRepository BankCardRepository) {
        this.BankCardRepository = BankCardRepository;
    }

    public void addNewBankCard(BankCard BankCard) {
        Optional<BankCard> BankCardOptional = BankCardRepository
                .findBankCardByNfcId(BankCard.getNfcId());
        if (BankCardOptional.isPresent()) {
            throw new IllegalStateException("Bank card already exists!");
        }
        BankCardRepository.save(BankCard);
        System.out.println(String.format("The following bank card has been registered: \n", BankCard.toString()));
    }

    public void deleteBankCard(Integer nfcId) {
        Optional<BankCard> BankCardOptional = BankCardRepository
                .findBankCardByNfcId(nfcId);
        BankCard bankCard;
        if (!BankCardOptional.isPresent()) {
            throw new IllegalStateException("Bank card does not exists!");
        } else {
            bankCard = BankCardOptional.get();
            BankCardRepository.deleteById(bankCard.getUuid());
        }
    }

    @Transactional
    public void updateBankCard(Integer nfcIdPath, Integer nfcIdBody, LocalDate expiryDate, String iban) {
        BankCard BankCard = BankCardRepository.findBankCardByNfcId(nfcIdPath)
                .orElseThrow(() -> new IllegalStateException("BankCard uuid does not exist"));

        if (expiryDate != null && BankCard.getExpiryDate() != expiryDate && LocalDate.now().isAfter(expiryDate)) {
            BankCard.setExpiryDate(expiryDate);
        }
        if (iban != null && iban.length() > 0 && BankCard.getIban() != iban) {
            BankCard.setIban(iban);
        }
        if (nfcIdBody != null && nfcIdBody > 0 && BankCard.getNfcId() != nfcIdBody) {
            BankCard.setNfcId(nfcIdBody);
        }
    }
}
