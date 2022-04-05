package com.trip.serviceterminal.ticket;

public class Ticket {
    private Long id;
    private Float price;
    private String description;

    public Ticket() {
    }

    public Ticket(Long id, Float price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ticket id(Long id) {
        setId(id);
        return this;
    }

    public Ticket price(Float price) {
        setPrice(price);
        return this;
    }

    public Ticket description(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", price='" + getPrice() + "'" +
                ", description='" + getDescription() + "'" +
                "}";
    }

}
