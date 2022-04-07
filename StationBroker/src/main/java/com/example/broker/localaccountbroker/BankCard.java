package com.example.broker.localaccountbroker;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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
    private Boolean deleted;
    private Date createdAt;

    public BankCard() {
    }

    public BankCard(Long uuid, LocalDate expiryDate, Integer nfcId, String iban, Date createdAt, Boolean deleted) {
        this.uuid = uuid;
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
        this.createdAt = createdAt;
        this.deleted = deleted;
    }

    public BankCard(Long uuid, LocalDate expiryDate, Integer nfcId) {
        this.uuid = uuid;
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
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

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "uuid=" + uuid +
                ", expiryDate=" + expiryDate +
                ", nfcId=" + nfcId +
                ", iban='" + iban + '\'' +
                ", createdAt=" + createdAt +
                ", deleted=" + deleted +
                '}';
    }
}
