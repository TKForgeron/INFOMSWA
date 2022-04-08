package com.trip.serviceterminal.bankcard;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long uuid;
    private LocalDate expiryDate;
    private Integer nfcId;
    private String iban;
    private Date createdAt;

    public BankCard() {
    }

    public BankCard(LocalDate expiryDate, Integer nfcId, String iban, Date createdAt) {
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
        this.createdAt = createdAt;
    }

    public BankCard(LocalDate expiryDate, Integer nfcId, String iban) {
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
    }

    public BankCard(Long uuid, LocalDate expiryDate, Integer nfcId, String iban) {
        this.uuid = uuid;
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
    }

    public BankCard(Long uuid, LocalDate expiryDate, Integer nfcId, String iban, Date createdAt) {
        this.uuid = uuid;
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
        this.createdAt = createdAt;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(this.expiryDate);
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

    @Override
    public String toString() {
        return "BankCard{" +
                "uuid=" + uuid +
                ", expiryDate=" + expiryDate +
                ", nfcId=" + nfcId +
                ", iban='" + iban + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
