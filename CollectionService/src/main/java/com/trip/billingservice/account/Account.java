// package com.trip.accountservice.subscription;

// import org.springframework.data.annotation.LastModifiedDate;
// import org.springframework.format.annotation.DateTimeFormat;

// import javax.persistence.*;
// import java.time.LocalDate;
// import java.util.Date;
// import java.util.List;
// import java.util.ArrayList;

// public class Account {
// private Long uuid;
// private LocalDate expiryDate;
// private Integer nfcId;
// private String iban;
// private String subscriptionIds;
// private Boolean deleted;
// private Date createdAt;
// private Date updatedOn;

// public Account() {
// }

// public Account(Long uuid, LocalDate expiryDate, Integer nfcId, String iban,
// Date updatedOn) {
// this.uuid = uuid;
// this.expiryDate = expiryDate;
// this.nfcId = nfcId;
// this.iban = iban;
// this.updatedOn = updatedOn;
// }

// public Account(Long uuid, LocalDate expiryDate, Integer nfcId, String iban,
// List<Integer> subscriptionIds,
// Boolean deleted, Date createdAt, Date updatedOn) {
// this.uuid = uuid;
// this.expiryDate = expiryDate;
// this.nfcId = nfcId;
// this.iban = iban;
// this.setSubscriptionIds(subscriptionIds);
// this.deleted = deleted;
// this.createdAt = createdAt;
// this.updatedOn = updatedOn;
// }

// public Long getUuid() {
// return uuid;
// }

// public void setUuid(Long uuid) {
// this.uuid = uuid;
// }

// public LocalDate getExpiryDate() {
// return expiryDate;
// }

// public void setExpiryDate(LocalDate expiryDate) {
// this.expiryDate = expiryDate;
// }

// public Integer getNfcId() {
// return nfcId;
// }

// public void setNfcId(Integer nfcId) {
// this.nfcId = nfcId;
// }

// public String getIban() {
// return iban;
// }

// public void setIban(String iban) {
// this.iban = iban;
// }

// public Boolean isDeleted() {
// return this.deleted;
// }

// public void setDeleted(Boolean deleted) {
// this.deleted = deleted;
// }

// public Date getCreatedAt() {
// return createdAt;
// }

// public void setCreatedAt(Date createdAt) {
// this.createdAt = createdAt;
// }

// public Date getUpdatedOn() {
// return updatedOn;
// }

// public void setUpdatedOn(Date updatedOn) {
// this.updatedOn = updatedOn;
// }

// public List<Integer> getSubscriptionIds() {
// String[] subArr = this.subscriptionIds.split(",",
// this.subscriptionIds.length());
// List<Integer> subIntList = new ArrayList<>();

// for (String s : subArr) {
// subIntList.add(Integer.parseInt(s));
// }
// return subIntList;
// }

// public void setSubscriptionIds(List<Integer> subscriptions) {
// List<String> subStrList = new ArrayList<>();

// for (Integer s : subscriptions) {
// subStrList.add(s.toString());
// }
// String subscriptionString = String.join(",", subStrList);
// this.subscriptionIds = subscriptionString;
// }

// @Override
// public String toString() {
// return "{" +
// " uuid='" + getUuid() + "'" +
// ", expiryDate='" + getExpiryDate() + "'" +
// ", nfcId='" + getNfcId() + "'" +
// ", iban='" + getIban() + "'" +
// ", subscriptionIds='" + getSubscriptionIds() + "'" +
// ", deleted='" + isDeleted() + "'" +
// ", createdAt='" + getCreatedAt() + "'" +
// ", updatedOn='" + getUpdatedOn() + "'" +
// "}";
// }

// }