package com.trip.serviceterminal.bankcard;

// import javax.persistence.*;
import java.time.LocalDate;

public class BankCard {
    private LocalDate expiryDate;
    private Integer nfcId;
    private String iban;

    public BankCard() {
    }

    public BankCard(LocalDate expiryDate, Integer nfcId, String iban) {
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
    }

    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getNfcId() {
        return this.nfcId;
    }

    public void setNfcId(Integer nfcId) {
        this.nfcId = nfcId;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BankCard expiryDate(LocalDate expiryDate) {
        setExpiryDate(expiryDate);
        return this;
    }

    public BankCard nfcId(Integer nfcId) {
        setNfcId(nfcId);
        return this;
    }

    public BankCard iban(String iban) {
        setIban(iban);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                ", expiryDate='" + getExpiryDate() + "'" +
                ", nfcId='" + getNfcId() + "'" +
                ", iban='" + getIban() + "'" +
                "}";
    }

}
