package com.trip.serviceterminal.bankcard;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/serviceterminal/bankcard/")
public class BankCardController {

    private final BankCardService BankCardService;

    @Autowired
    public BankCardController(BankCardService BankCardService) {
        this.BankCardService = BankCardService;
    }

    @PostMapping(path = "add")
    public void registerNewBankCard(@RequestBody BankCard BankCard) {
        BankCardService.addNewBankCard(BankCard);
    }

    @DeleteMapping(path = "{nfcId}")
    public void deleteBankCard(
            @PathVariable("nfcId") Integer nfcId) {
        BankCardService.deleteBankCard(nfcId);
    }

    @PutMapping(path = "{nfcIdPath}")
    public void updateBankCard(
            @PathVariable("nfcIdPath") Integer nfcIdPath,
            @RequestBody(required = false) Integer nfcId, // If one registers a new NFC-card, replacing the old
                                                          // (e.g. expired) one
            @RequestBody(required = false) LocalDate expiryDate,
            @RequestBody(required = false) String iban) {
        BankCardService.updateBankCard(nfcIdPath, nfcId, expiryDate, iban);
    }
}
