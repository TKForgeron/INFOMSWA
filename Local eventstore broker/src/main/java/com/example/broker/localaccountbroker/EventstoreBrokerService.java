package com.example.broker.localaccountbroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventstoreBrokerService {

    private final EventstoreRepository EventstoreRepository;

    @Autowired
    public EventstoreBrokerService(EventstoreRepository EventstoreRepository) {
        this.EventstoreRepository = EventstoreRepository;
    }
}