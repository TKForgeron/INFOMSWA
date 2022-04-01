package com.example.broker.stationbroker;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class BankCard {
    @Id
    @SequenceGenerator(name = "nfc_sequence", sequenceName = "nfc_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nfc_sequence")
    private Long uuid;
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

    public Long getUuid() {
        return this.uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
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

    public BankCard uuid(Long uuid) {
        setUuid(uuid);
        return this;
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
                " uuid='" + getUuid() + "'" +
                ", expiryDate='" + getExpiryDate() + "'" +
                ", nfcId='" + getNfcId() + "'" +
                ", iban='" + getIban() + "'" +
                "}";
    }

}
