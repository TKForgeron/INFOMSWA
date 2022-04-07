package com.example.broker.localaccountbroker;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping
public class EventstoreBrokerController {

    private final EventstoreBrokerService EventstoreBrokerService;
    private final EventstoreRepository eventstoreRepository;

    public EventstoreBrokerController(EventstoreBrokerService EventstoreBrokerService, EventstoreRepository eventstoreRepository) {
        this.EventstoreBrokerService = EventstoreBrokerService;
        this.eventstoreRepository = eventstoreRepository;
    }
}