package com.trip.serviceterminal.bankcard;

// import javax.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
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

    public BankCard() {
    }

    public BankCard(Long uuid, LocalDate expiryDate, Integer nfcId, String iban) {
        this.uuid = uuid;
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
    }

    public BankCard(LocalDate expiryDate, Integer nfcId, String iban) {
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
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

    @Override
    public String toString() {
        return "BankCard{" +
                "uuid=" + uuid +
                ", expiryDate=" + expiryDate +
                ", nfcId=" + nfcId +
                ", iban='" + iban + '\'' +
                '}';
    }
}
