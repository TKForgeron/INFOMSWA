package com.trip.billingservice.passengerRoute;

import jdk.jfr.Event;
import net.bytebuddy.implementation.bytecode.collection.ArrayLength;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;


@Service
public class BillingService {

    private final BillingRepository billingRepository;

    public BillingService(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
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

        return billingRepository.findTopByUpdatedOn().orElse(date);
    }

    public Subscription[] fetchSubscriptions() throws URISyntaxException {

        URI uri = new URI("http://localhost:4900/subscription/all");
        RestTemplate restTemplate = new RestTemplate();
        Subscription[] allPossibleSubscriptions = restTemplate.getForObject(uri, Subscription[].class);

        return allPossibleSubscriptions;
    }

    public List<Subscription> fetchUserSubscriptions(Long uuid) throws URISyntaxException {
        URI uri = new URI(String.format("http://localhost:7100/from_billing/account/%1$s/get/subscriptions", uuid));
        RestTemplate restTemplate = new RestTemplate();
        Long[] subIds = restTemplate.getForObject(uri, Long[].class);
        Subscription[] allPossibleSubscriptions = fetchSubscriptions();

        List<Subscription> userSubs = new ArrayList<>();
        for (Subscription sub : allPossibleSubscriptions) {

            if (Arrays.asList(subIds).contains(sub.getId())) {
                userSubs.add(sub);
            }
        }
        return userSubs;
    }

    public Float calculatePrice(Long uuid, String startLocation, String endLocation) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI routeURI = new URI(String.format("http://localhost:4500/api/v1/route/%1$s/%2$s", startLocation, endLocation));
        Route routeEntity = restTemplate.getForObject(routeURI, Route.class);
        Float price = routeEntity.getPrice();
        Integer tycoon = routeEntity.getTycoonId();
        List<Subscription> subscriptions = fetchUserSubscriptions(uuid);
        System.out.println(subscriptions);

        for (Subscription s:subscriptions) {
            if(s.getTycoonId() == tycoon) {
                Float discount = price * s.getDiscountPercentage()/100;
                price -= discount;
            }
        }

        return price;
    }

    public PassengerRoute buildRoutes(List<EventStore> eventStores) throws URISyntaxException {
        if (eventStores.size() > 1) {
            EventStore startEvent = eventStores.get(0);
            EventStore endEvent = eventStores.get(1);
            PassengerRoute route = new PassengerRoute(
                    startEvent.getUUID(),
                    startEvent.getDate(),
                    endEvent.getDate(),
                    startEvent.getLocation(),
                    endEvent.getLocation(),
                    calculatePrice(
                            startEvent.getUUID(),
                            startEvent.getLocation(),
                            endEvent.getLocation()
                    )
            );
            return route;
        }
        return null;
    }
}