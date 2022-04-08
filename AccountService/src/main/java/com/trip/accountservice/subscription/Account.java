package com.trip.accountservice.subscription;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
// import java.util.Array;

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
    private String subscriptionIds;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updatedOn", nullable = false)
    @LastModifiedDate
    private Date updatedOn;

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

    public Account(Long uuid, LocalDate expiryDate, Integer nfcId, String iban, List<Integer> subscriptionIds,
            Date updatedOn) {
        this.uuid = uuid;
        this.expiryDate = expiryDate;
        this.nfcId = nfcId;
        this.iban = iban;
        this.setSubscriptionIds(subscriptionIds);
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

    public List<Integer> getSubscriptionIds() {
        String[] subArr = this.subscriptionIds.split(",", this.subscriptionIds.length());
        List<Integer> subIntList = new ArrayList<>();

        for (String s : subArr) {
            subIntList.add(Integer.parseInt(s));
        }
        return subIntList;
    }

    public void setSubscriptionIds(List<Integer> subscriptions) {
        List<String> subStrList = new ArrayList<>();

        for (Integer s : subscriptions) {
            subStrList.add(s.toString());
        }
        String subscriptionString = String.join(",", subStrList);
        this.subscriptionIds = subscriptionString;
    }

    @Override
    public String toString() {
        return "{" +
                " uuid='" + getUuid() + "'" +
                ", expiryDate='" + getExpiryDate() + "'" +
                ", nfcId='" + getNfcId() + "'" +
                ", iban='" + getIban() + "'" +
                ", subscriptionIds='" + getSubscriptionIds() + "'" +
                ", updatedOn='" + getUpdatedOn() + "'" +
                "}";
    }

}