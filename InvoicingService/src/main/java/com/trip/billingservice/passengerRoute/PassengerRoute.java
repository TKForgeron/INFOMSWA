package com.trip.billingservice.passengerRoute;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class PassengerRoute {
    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private Long id;
    private Long uuid;
    private Date startDate;
    private Date endDate;
    private String beginLocation;
    private String endLocation;
    private Float price;

    public PassengerRoute() {
    }

    public PassengerRoute(Long uuid, Date startDate, Date endDate, String beginLocation, String endLocation, Float price) {
        this.uuid = uuid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.beginLocation = beginLocation;
        this.endLocation = endLocation;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBeginLocation() {
        return beginLocation;
    }

    public void setBeginLocation(String beginLocation) {
        this.beginLocation = beginLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PassengerRoute{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", beginLocation='" + beginLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", price=" + price +
                '}';
    }
}
