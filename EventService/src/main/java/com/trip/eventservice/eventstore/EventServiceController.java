package com.trip.eventservice.eventstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class EventServiceController {

    private final EventServiceService eventServiceService;
    private final EventServiceRepository eventServiceRepository;

    @Autowired
    public EventServiceController(EventServiceService eventServiceService, EventServiceRepository eventServiceRepository) {
        this.eventServiceService = eventServiceService;
        this.eventServiceRepository = eventServiceRepository;
    }


}