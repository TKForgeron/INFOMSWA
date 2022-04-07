package com.trip.serviceterminal.subscription;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subscription {
    @Id
    private Long id;
    private String tycoon;
    private Integer discountPercentage;
    private String description;

    public Subscription() {
    }

    public Subscription(Long id, String tycoon, Integer discountPercentage, String description) {
        this.id = id;
        this.tycoon = tycoon;
        this.discountPercentage = discountPercentage;
        this.description = description;
    }

    public Subscription(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTycoon() {
        return tycoon;
    }

    public void setTycoon(String tycoon) {
        this.tycoon = tycoon;
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
        return "Subscript{" +
                "id=" + id +
                ", tycoon='" + tycoon + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", description='" + description + '\'' +
                '}';
    }
}
