package com.trip.subscriptionservice.subscription;

import javax.persistence.*;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @Id
    @SequenceGenerator(name = "subscription_sequence", sequenceName = "subscription_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_sequence")
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTycoon() {
        return this.tycoon;
    }

    public void setTycoon(String tycoon) {
        this.tycoon = tycoon;
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

    public Subscription tycoon(String tycoon) {
        setTycoon(tycoon);
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

    // own implementation: A-B equals B-A, id doesn't matter
    public boolean isLike(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Subscription)) {
            return false;
        }
        Subscription sub = (Subscription) o;
        return tycoon.equals(sub.tycoon) && discountPercentage.equals(sub.discountPercentage)
                && description.equals(sub.description);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", tycoon='" + getTycoon() + "'" +
                ", discountPercentage='" + getDiscountPercentage() + "'" +
                ", description='" + getDescription() + "'" +
                "}";
    }

}
