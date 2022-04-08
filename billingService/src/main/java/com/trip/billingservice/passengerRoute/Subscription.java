package com.trip.billingservice.passengerRoute;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subscription {
    @Id
    private Long id;
    private Integer tycoonId;
    private Integer discountPercentage;
    private String description;

    public Subscription() {
    }

    public Subscription(Long id, Integer tycoonId, Integer discountPercentage, String description) {
        this.id = id;
        this.tycoonId = tycoonId;
        this.discountPercentage = discountPercentage;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTycoonId() {
        return tycoonId;
    }

    public void setTycoonId(Integer tycoonId) {
        this.tycoonId = tycoonId;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", tycoonId=" + tycoonId +
                ", discountPercentage=" + discountPercentage +
                ", description='" + description + '\'' +
                '}';
    }
}