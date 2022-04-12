package com.trip.collectionservice.pspObject;

import java.util.Date;
import com.trip.collectionservice.passengerRoute.PassengerRoute;
// import com.trip.collectionservice.;

public class PSPObject {
    private Long id;
    private String iban;
    private PassengerRoute[] passengerRoutes;

    public PSPObject() {
    }

    public PSPObject(Long id, PassengerRoute[] prs, String iban) {
        this.id = id;
        this.iban = iban;
        this.passengerRoutes = prs;
    }

    public PSPObject(Long id, String iban, PassengerRoute[] prs) {
        this.id = id;
        this.iban = iban;
        this.passengerRoutes = prs;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return this.iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public PassengerRoute[] getPassengerRoutes() {
        return this.passengerRoutes;
    }

    public void setPassengerRoutes(PassengerRoute[] passengerRoutes) {
        this.passengerRoutes = passengerRoutes;
    }

    public PSPObject id(Long id) {
        setId(id);
        return this;
    }

    public PSPObject iban(String iban) {
        setIban(iban);
        return this;
    }

    public PSPObject passengerRoutes(PassengerRoute[] passengerRoutes) {
        setPassengerRoutes(passengerRoutes);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", iban='" + getIban() + "'" +
                ", passengerRoutes='" + getPassengerRoutes() + "'" +
                "}";
    }

}
