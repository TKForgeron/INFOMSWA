package com.example.demo.nfcReader;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class Passenger {
    @Id
    @SequenceGenerator(
            name = "passenger_id",
            sequenceName = "passenger_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "passenger_id"
    )
    private Long uuid;
    private Long nfcId;

    public Passenger() {
    }

    public Passenger(Long uuid, Long nfcId) {
        this.uuid = uuid;
        this.nfcId = nfcId;
    }

    public Passenger(Long nfcId) {
        this.nfcId = nfcId;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Long getNfcId() {
        return nfcId;
    }

    public void setNfcId(Long nfcId) {
        this.nfcId = nfcId;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "uuid=" + uuid +
                ", nfcId=" + nfcId +
                '}';
    }
}
