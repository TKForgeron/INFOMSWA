package com.trip.collectionservice.collection;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import com.trip.collectionservice.passengerRoute.PassengerRoute;
import com.trip.collectionservice.pspObject.PSPObject;

@Service
public class CollectionService {

    public PSPObject collectMoneyForUuid(Long uuid) throws URISyntaxException {
        URI invoice_uri = new URI(String.format("http://localhost:7300/invoicing/invoices/%s", String.valueOf(uuid)));
        RestTemplate restTemplate = new RestTemplate();
        PassengerRoute[] passengerRoutes = restTemplate.getForObject(invoice_uri, PassengerRoute[].class);

        URI iban_uri = new URI(String.format("http://localhost:7100/account/%s/iban", String.valueOf(uuid)));
        restTemplate = new RestTemplate();
        String iban = restTemplate.getForObject(iban_uri, String.class);

        PSPObject PSPObject = new PSPObject(passengerRoutes[0].getId(), iban, passengerRoutes);

        return PSPObject;
    }
}