package com.trip.eventservice.eventstore;

import org.springframework.stereotype.Service;

@Service
public class EventServiceService {

    private final EventServiceRepository eventServiceRepository;

    public EventServiceService(EventServiceRepository eventServiceRepository) {
        this.eventServiceRepository = eventServiceRepository;
    }
}
