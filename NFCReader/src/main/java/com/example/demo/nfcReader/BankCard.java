package com.example.demo.nfcReader;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class BankCard {
    @Id
    @SequenceGenerator(
            name = "nfc_sequence",
            sequenceName = "nfc_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "nfc_sequence"
    )
    private Long id;
    private LocalDate expiryDate;
    private Integer nfcId;

    public BankCard() {
    }

    public BankCard(Long id, LocalDate expiryDate, Integer nfcId) {
        this.id = id;
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
    }

    public BankCard(LocalDate expiryDate, Integer nfcId) {
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getNfcId() {
        return nfcId;
    }

    public void setNfcId(Integer nfcId) {
        this.nfcId = nfcId;
    }

    @Override
    public String toString() {
        return "NFCReader{" +
                "id=" + id +
                ", expiryDate=" + expiryDate +
                ", nfcId=" + nfcId +
                '}';
    }
}

