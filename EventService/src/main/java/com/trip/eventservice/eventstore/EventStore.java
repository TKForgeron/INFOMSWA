package com.trip.eventservice.eventstore;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class EventStore {
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
    private Long UUID;
    private Date date;
    private String location;
    private Integer tycoon;

    public EventStore() {
    }

    public EventStore(Long UUID, Date date, String location, Integer tycoon) {
        this.UUID = UUID;
        this.date = date;
        this.location = location;
        this.tycoon = tycoon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUUID() {
        return UUID;
    }

    public void setUUID(Long UUID) {
        this.UUID = UUID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getTycoon() {
        return tycoon;
    }

    public void setTycoon(Integer tycoon) {
        this.tycoon = tycoon;
    }

    @Override
    public String toString() {
        return "EventStore{" +
                "id=" + id +
                ", UUID=" + UUID +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", tycoon=" + tycoon +
                '}';
    }
}
