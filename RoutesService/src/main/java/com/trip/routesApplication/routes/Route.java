package com.trip.routesApplication.routes;

import javax.persistence.*;

@Entity
@Table
public class Route {
    @Id
    @SequenceGenerator(name = "route_sequence", sequenceName = "route_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_sequence")
    private Long id;
    private String stationA;
    private String stationB;
    private Float price;
    private String tycoon;

    public Route() {
    }

    public Route(Long id, String stationA, String stationB, Float price, String tycoon) {
        this.id = id;
        this.stationA = stationA;
        this.stationB = stationB;
        this.price = price;
        this.tycoon = tycoon;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationA() {
        return this.stationA;
    }

    public void setStationA(String stationA) {
        this.stationA = stationA;
    }

    public String getStationB() {
        return this.stationB;
    }

    public void setStationB(String stationB) {
        this.stationB = stationB;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getTycoon() {
        return this.tycoon;
    }

    public void setTycoon(String tycoon) {
        this.tycoon = tycoon;
    }

    public Route id(Long id) {
        setId(id);
        return this;
    }

    public Route stationA(String stationA) {
        setStationA(stationA);
        return this;
    }

    public Route stationB(String stationB) {
        setStationB(stationB);
        return this;
    }

    public Route price(Float price) {
        setPrice(price);
        return this;
    }

    public Route tycoon(String tycoon) {
        setTycoon(tycoon);
        return this;
    }

    // own implementation: A-B equals B-A, id doesn't matter
    // @Override
    // public boolean equals(Object o) {
    // if (o == this)
    // return true;
    // if (!(o instanceof Route)) {
    // return false;
    // }
    // Route route = (Route) o;
    // return (Objects.equals(stationA, route.stationA) && Objects.equals(stationB,
    // route.stationB)
    // || Objects.equals(stationA, route.stationB) && Objects.equals(stationB,
    // route.stationA))
    // && Objects.equals(price, route.price) && Objects.equals(tycoon,
    // route.tycoon);
    // }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", stationA='" + getStationA() + "'" +
                ", stationB='" + getStationB() + "'" +
                ", price='" + getPrice() + "'" +
                ", tycoon='" + getTycoon() + "'" +
                "}";
    }

}