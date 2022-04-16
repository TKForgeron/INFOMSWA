package com.trip.accountservice.subscription;

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
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTycoonId() {
        return this.tycoonId;
    }

    public void setTycoonId(Integer tycoonId) {
        this.tycoonId = tycoonId;
    }

    public Integer getDiscountPercentage() {
        return this.discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subscription id(Long id) {
        setId(id);
        return this;
    }

    public Subscription tycoonId(Integer tycoonId) {
        setTycoonId(tycoonId);
        return this;
    }

    public Subscription discountPercentage(Integer discountPercentage) {
        setDiscountPercentage(discountPercentage);
        return this;
    }

    public Subscription description(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", tycoonId='" + getTycoonId() + "'" +
                ", discountPercentage='" + getDiscountPercentage() + "'" +
                ", description='" + getDescription() + "'" +
                "}";
    }

}
