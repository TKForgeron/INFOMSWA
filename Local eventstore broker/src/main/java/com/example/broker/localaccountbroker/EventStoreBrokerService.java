package com.example.broker.localaccountbroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public List<EventStore> getNewEventStores(Date lastUpdatedOn) {
        Optional<List<EventStore>> optionalAccountList= eventStoreRepository
                .findAccountsByUpdatedOnAfter(lastUpdatedOn);
        return optionalAccountList.orElse(null);
    }
}