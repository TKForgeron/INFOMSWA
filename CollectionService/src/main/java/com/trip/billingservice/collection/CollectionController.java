package com.trip.collectionservice.collection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import com.trip.collectionservice.pspObject.PSPObject;

@RestController
public class CollectionController {

    // private final BillingRepository billingRepository;
    private final CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping(path = "collect/{uuid}")
    public PSPObject collectMoney(@PathVariable("uuid") Long uuid) throws URISyntaxException {
        return collectionService.collectMoneyForUuid(uuid);
    }

}
