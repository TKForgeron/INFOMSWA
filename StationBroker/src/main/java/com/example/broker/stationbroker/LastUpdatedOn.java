package com.example.broker.stationbroker;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class LastUpdatedOn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date lastUpdatedOn;

    public LastUpdatedOn() {
    }

    public LastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public LastUpdatedOn(Long id, Date lastUpdatedOn) {
        this.id = id;
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    @Override
    public String toString() {
        return "LastUpdatedOn{" +
                "id=" + id +
                ", lastUpdatedOn=" + lastUpdatedOn +
                '}';
    }
}

