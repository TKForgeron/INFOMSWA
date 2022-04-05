package com.trip.accountservice.subscription;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table
public class Account {
    @Id
    @Column(name="uuid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long uuid;
    private LocalDate expiryDate;
    private Integer nfcId;
    private String iban;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "updatedOn", nullable = false)
    @LastModifiedDate
    private Date updatedOn;
    //private String subscriptions;


    public Account() {
    }

    public Account(LocalDate expiryDate, Integer nfcId, String iban) {
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
    }

    public Account(Long uuid, LocalDate expiryDate, Integer nfcId, String iban) {
        this.uuid = uuid;
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
    }

    public Account(Long uuid, LocalDate expiryDate, Integer nfcId, String iban, Date updatedOn) {
        this.uuid = uuid;
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
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
                ", updatedOn=" + updatedOn +
                '}';
    }
}