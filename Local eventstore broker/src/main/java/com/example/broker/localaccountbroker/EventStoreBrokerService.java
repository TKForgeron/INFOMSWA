package com.example.broker.localaccountbroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventStoreBrokerService {

    private final EventStoreRepository eventStoreRepository;

    @Autowired
    public EventStoreBrokerService(EventStoreRepository eventStoreRepository) {
        this.eventStoreRepository = eventStoreRepository;
    }

    public List<EventStore> getEventStores() {
        return eventStoreRepository.findAll();
    }

    public void registerEventStore(EventStore eventStore) {
        eventStoreRepository.save(eventStore);
    }
}