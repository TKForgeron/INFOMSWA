package com.trip.nfcreaderapp.nfcReader;

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

    public BankCard() {
    }

    public BankCard(Long uuid, LocalDate expiryDate, Integer nfcId) {
        this.uuid = uuid;
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
    }

    public BankCard(LocalDate expiryDate, Integer nfcId) {
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long id) {
        this.uuid = id;
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
                "id=" + uuid +
                ", expiryDate=" + expiryDate +
                ", nfcId=" + nfcId +
                '}';
    }
}
