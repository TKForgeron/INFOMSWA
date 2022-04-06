package com.example.broker.stationbroker;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class Account {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uuid;
    private LocalDate expiryDate;
    private Integer nfcId;
    private String iban;
    private Date createdAt;
    private Date updatedOn;
    //private List<Long> subscriptionIds;

    public Account() {
    }

    public Account(Long uuid, LocalDate expiryDate, Integer nfcId, String iban, Date createdAt, Date updatedOn) {
        this.uuid = uuid;
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
        this.createdAt = createdAt;
        this.updatedOn = updatedOn;
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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "Account{" +
                "uuid=" + uuid +
                ", expiryDate=" + expiryDate +
                ", nfcId=" + nfcId +
                ", iban='" + iban + '\'' +
                ", createdAt=" + createdAt +
                ", updatedOn=" + updatedOn +
                '}';
    }
}