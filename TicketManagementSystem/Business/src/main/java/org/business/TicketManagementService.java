package org.business;

import org.model.Event;
import org.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketManagementService implements ITicketManagementService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EventTypeRepository eventTypeRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TicketCategoryRepository ticketCategoryRepository;

    @Override
    public List<Event> findAllEvents()
    {
        return eventRepository.findAll();
    }

}
