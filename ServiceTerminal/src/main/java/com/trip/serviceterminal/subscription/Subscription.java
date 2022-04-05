package com.trip.serviceterminal.subscription;

public class Subscription {
    private Long id;
    private Integer discountPercentage;
    private String description;

    public Subscription() {
    }

    public Subscription(Long id) {
        this.id = id;
    }

    public Subscription(Long id, Integer discountPercentage, String description) {
        this.id = id;
        this.discountPercentage = discountPercentage;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", discountPercentage='" + getDiscountPercentage() + "'" +
                ", description='" + getDescription() + "'" +
                "}";
    }

}
