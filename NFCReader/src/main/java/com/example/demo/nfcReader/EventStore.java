package com.example.demo.nfcReader;

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
    private Integer UUID;
    private Date date;
    private String location;

    public EventStore() {
    }

    public EventStore(Long id, Integer UUID, Date date, String location) {
        this.id = id;
        this.UUID = UUID;
        this.date = date;
        this.location = location;
    }

    public EventStore(Integer UUID, Date date, String location) {
        this.UUID = UUID;
        this.date = date;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUUID() {
        return UUID;
    }

    public void setUUID(Integer UUID) {
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

    @Override
    public String toString() {
        return "EventStore{" +
                "id=" + id +
                ", UUID=" + UUID +
                ", date=" + date +
                ", location='" + location + '\'' +
                '}';
    }
}
