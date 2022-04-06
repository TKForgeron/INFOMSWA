package com.trip.subscriptionservice.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository SubscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository SubscriptionRepository) {
        this.SubscriptionRepository = SubscriptionRepository;
    }

    public List<Subscription> getSubscriptions() {
        return SubscriptionRepository.findAll();
    }

    public Subscription addSubscription(Subscription subscription) {
        Optional<Subscription> subscriptionOptional = SubscriptionRepository
                .findByDescription(subscription.getDescription());
        if (subscriptionOptional.isPresent()) {
            Subscription subscriptionFromDB = subscriptionOptional.get();
            if (subscriptionFromDB.isLike(subscription)) {
                throw new IllegalStateException("subscription already exists!");
            }
        }
        SubscriptionRepository.save(subscription);
        return subscription;
    }

    @Transactional
    public Subscription updateSubscription(Long subscriptionIdCurrent, Subscription subscriptionToBe) {
        Subscription subscriptionCurrent = SubscriptionRepository.findById(subscriptionIdCurrent)
                .orElseThrow(() -> new IllegalStateException("subscriptionId does not exist!"));

        String tycoon = subscriptionToBe.getTycoon();
        Integer discountPercentage = subscriptionToBe.getDiscountPercentage();
        String description = subscriptionToBe.getDescription();

        if (tycoon != null && tycoon.length() > 0) {
            subscriptionCurrent.setTycoon(tycoon);
        }
        if (discountPercentage != null) {
            subscriptionCurrent.setDiscountPercentage(discountPercentage);
        }
        if (description != null && description.length() > 0) {
            subscriptionCurrent.setDescription(description);
        }

        return subscriptionCurrent;

    }

    public Subscription deleteSubscription(Long subscriptionIdCurrent, Subscription iKnowWhatSubscriptionIAmDeleting) {
        Subscription subscriptionCurrent = SubscriptionRepository.findById(subscriptionIdCurrent)
                .orElseThrow(() -> new IllegalStateException(
                        "subscriptionId: " + subscriptionIdCurrent + "does not exist!"));
        if (subscriptionCurrent.isLike(iKnowWhatSubscriptionIAmDeleting)) {
            SubscriptionRepository.deleteById(subscriptionIdCurrent);

        } else {
            throw new IllegalStateException("You are not authorized to delete!");

        }
        return subscriptionCurrent;

    }
    // public Boolean isValid(Integer nfcId) {
    // Optional<Subscription> accountOptional = SubscriptionRepository
    // .findAccountByNfcId(nfcId);
    // if (accountOptional.isPresent()) {
    // return true;
    // }
    // return false;
    // }

    // public void addNewSubscription(Subscription Subscription) {
    // Optional<Subscription> subscriptionOptional = SubscriptionRepository
    // .findSubscriptionByEmail(Subscription.getEmail());
    // if (subscriptionOptional.isPresent()) {
    // throw new IllegalStateException("email taken");
    // }
    // SubscriptionRepository.save(Subscription);
    // }

    // public void deleteSubscription(Long subscriptionId) {
    // boolean exists = SubscriptionRepository.existsById(subscriptionId);
    // if (!exists) {
    // throw new IllegalStateException(
    // "subscription with id: " + subscriptionId + "doesn't exist");
    // }
    // SubscriptionRepository.deleteById(subscriptionId);
    // }

    // @Transactional
    // public void updateSubscription(Long subscriptionId,
    // String name,
    // String email) {
    // Subscription Subscription = SubscriptionRepository.findById(subscriptionId)
    // .orElseThrow(() -> new IllegalStateException("subscriptionID doesn't
    // exist"));

    // if (name != null && name.length() > 0 && Subscription.getName() != name) {
    // Subscription.setName(name);
    // }
    // if (email != null && email.length() > 0 && Subscription.getEmail() != email)
    // {
    // Subscription.setEmail(email);
    // }
    // }
}
