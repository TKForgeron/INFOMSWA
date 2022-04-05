package com.example.demo.student;

import com.trip.accountservice.subscription.LastDateInRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AccountService {

    private final LastDateInRepository LastDateInRepository;

    public AccountService(LastDateInRepository LastDateInRepository) {
        this.LastDateInRepository = LastDateInRepository;
    }

    public Optional<Date> lastUpdatedOn(){
        Optional<Date> lastUpdated = LastDateInRepository.findTopByUpdatedOn();
        if (lastUpdated.isPresent()) {
            return lastUpdated;
        }
        return null;
    }
}
