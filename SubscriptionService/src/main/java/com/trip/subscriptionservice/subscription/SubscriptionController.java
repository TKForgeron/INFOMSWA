package com.trip.subscriptionservice.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "subscription/")
public class SubscriptionController {

    private final SubscriptionService SubscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService SubscriptionService) {
        this.SubscriptionService = SubscriptionService;
    }

    @GetMapping(path = "all")
    public List<Subscription> getAllSubscription() {
        return SubscriptionService.getSubscriptions();
    }

    @PostMapping(path = "add")
    public Subscription addSubscription(@RequestBody Subscription subscription) {
        return SubscriptionService.addSubscription(subscription);
    }

    @PutMapping(path = "update/{subscriptionId}")
    public Subscription updateSubscription(@PathVariable("subscriptionId") Long subscriptionIdToBeUpdated,
            @RequestBody Subscription subscriptionToBe) {
        return SubscriptionService.updateSubscription(subscriptionIdToBeUpdated, subscriptionToBe);
    }

    @DeleteMapping(path = "delete/{subscriptionId}")
    public Subscription deleteSubscription(@PathVariable("subscriptionId") Long subscriptionId,
            @RequestBody Subscription iKnowWhatSubscriptionIAmDeleting) {
        return SubscriptionService.deleteSubscription(subscriptionId, iKnowWhatSubscriptionIAmDeleting);
    }

    // @PutMapping(path = "{studentId}")
    // public void updateStudent(
    // @PathVariable("studentId") Long studentId,
    // @RequestParam(required = false) String name,
    // @RequestParam(required = false) String email) {
    // SubscriptionService.updateStudent(studentId, name, email);
    // }
}
