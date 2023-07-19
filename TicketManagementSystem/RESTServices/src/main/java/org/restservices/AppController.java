package org.restservices;

import org.business.ITicketManagementService;
import org.business.TicketManagementService;
import org.model.*;
import org.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/management")
public class AppController {

    private static final String template = "Hello, %s!";

    @RequestMapping("/greeting")
    public  String greeting() {
        return String.format(template, "Alex");
    }

    @Autowired
    ITicketManagementService service;

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

    @RequestMapping(value = "/customers", method=RequestMethod.GET)
    public Customer[] getAllCustomers() {

        List<Customer> pos = customerRepository.findAll();
        System.out.println(pos);
        return pos.toArray(new Customer[0]);
    }

    @RequestMapping(value = "/eventTypes", method=RequestMethod.GET)
    public EventType[] getAllEventTypes() {

        List<EventType> pos = eventTypeRepository.findAll();
        System.out.println(pos);
        return pos.toArray(new EventType[0]);
    }

    @RequestMapping(value = "/venues", method=RequestMethod.GET)
    public Venue[] getAllVenues() {
        List<Venue> pos = venueRepository.findAll();
        System.out.println(pos);
        return pos.toArray(new Venue[0]);
    }

    @RequestMapping(value = "/events", method=RequestMethod.GET)
    public Event[] getAllEvents() {
        List<Event> pos = service.findAllEvents();
        System.out.println(pos);
        return pos.toArray(new Event[0]);
    }

    @RequestMapping(value = "/orders", method=RequestMethod.GET)
    public Order[] getAllOrders() {
        List<Order> pos = orderRepository.findAll();
        System.out.println(pos);
        return pos.toArray(new Order[0]);
    }

    @RequestMapping(value = "/ticketcategories", method=RequestMethod.GET)
    public TicketCategory[] getAllTicketCategories() {
        List<TicketCategory> pos = ticketCategoryRepository.findAll();
        System.out.println(pos);
        return pos.toArray(new TicketCategory[0]);
    }
}
