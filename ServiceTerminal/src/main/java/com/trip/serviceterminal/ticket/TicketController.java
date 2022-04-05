package com.trip.serviceterminal.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "api/v1/serviceterminal/ticket/")
public class TicketController {

    @GetMapping(path = "{ticketId}")
    public Ticket deleteSubscription(
            @PathVariable("ticketId") Long ticketId) {
        Ticket ticket = new Ticket(ticketId, (float) 20.0, "standard 24h-ticket");
        System.out.println(ticket.toString());
        System.out.println("...sold");
        return ticket;
    }
}
