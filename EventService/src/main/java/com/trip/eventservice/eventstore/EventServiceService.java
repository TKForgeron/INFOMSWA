package com.trip.eventservice.eventstore;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventServiceService {

    private final EventServiceRepository eventServiceRepository;

    public EventServiceService(EventServiceRepository eventServiceRepository) {
        this.eventServiceRepository = eventServiceRepository;
    }

    public List<EventStore> getEventStores() {
        return eventServiceRepository.findAll();
    }

    public Date lastUpdatedOn(){
        // If no date is found, set date to start of day
        Date date = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();

        return eventServiceRepository.findTopByUpdatedOn().orElse(date);
    }

    public List<EventStore> getNewEventStores(Date lastUpdatedOn) {
        Optional<List<EventStore>> optionalAccountList= eventServiceRepository
                .findAccountsByUpdatedOnAfter(lastUpdatedOn);
        return optionalAccountList.orElse(null);
    }
}
