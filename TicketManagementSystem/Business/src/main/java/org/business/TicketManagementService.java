package org.business;

import org.model.Customer;
import org.model.Event;
import org.model.Order;
import org.model.TicketCategory;
import org.model.errors.NoTicketsLeftException;
import org.persistence.CustomerRepository;
import org.persistence.EventTypeRepository;
import org.persistence.TicketCategoryRepository;
import org.persistence.EventRepository;
import org.persistence.VenueRepository;
import org.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Order> findAllOrdersForCustomer(String email) {
        return orderRepository.findOrdersByCustomerEmail(email);
    }


    @Override
    public List<Event> findEventsByVenueTypeAndByEventType(String location, String eventType) {
        return eventRepository.findByVenueTypeAndEventType_Name(location,eventType);
    }

    @Override
    public List<TicketCategory> findTicketCategoriesByEvent(Event event) {
        return ticketCategoryRepository.findTicketCategoriesByEvent(event);
    }

    public Optional<Order> saveOrder(String email, int ticketCategoryID, int numberOfTickets) throws NoTicketsLeftException {
        Customer customer = customerRepository.findCustomerByEmail(email);
        TicketCategory ticketCategory = ticketCategoryRepository.findTicketCategoryByTicketCategoryID(ticketCategoryID);

        if (customer == null || ticketCategory == null) {
            return Optional.empty();
        }

        int numberOfTicketsAvailable = ticketCategory.getNoAvailable();

        int numberOfTicketsLeft = numberOfTicketsAvailable - numberOfTickets;

        if(numberOfTicketsLeft < 0)
            throw new NoTicketsLeftException("Only " + numberOfTicketsAvailable + " tickets left");

        ticketCategory.setNoAvailable(numberOfTicketsLeft);
        Order order = new Order(ticketCategory, customer, numberOfTickets, LocalDateTime.now(),
                numberOfTickets * ticketCategory.getPrice());

        return Optional.of(orderRepository.save(order));
    }

    @Override
    public List<Event> findEventsByVenueType(String venueType) {
        return eventRepository.findByVenueType(venueType);
    }

    @Override
    public List<Event> findEventByEventType(String eventType) {
        return eventRepository.findByEventType_Name(eventType);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public Event findEventById(int eventId) {
        return eventRepository.findByEventID(eventId);
    }
}
