package org.business;

import org.model.Customer;
import org.model.Event;
import org.model.Order;
import org.model.TicketCategory;
import org.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
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

    @Override
    public List<Order> findAllOrdersForCustomer(int customerID) {
        return orderRepository.findOrdersByCustomer_CustomerID(customerID);
    }


    @Override
    public List<Event> findEventsByVenueLocationAndByEventType(String location, String eventType) {
        return eventRepository.findByVenueTypeAndEventType_Name(location,eventType);
    }

    @Override
    public List<TicketCategory> findTicketCategoriesByEvent(Event event) {
        return ticketCategoryRepository.findTicketCategoriesByEvent(event);
    }

    @Override
    public Order saveOrder(int customerID,int ticketCategoryID,int numberOfTickets)
    {
        Customer customer = customerRepository.findCustomerByCustomerID(customerID);
        TicketCategory ticketCategory = ticketCategoryRepository.findTicketCategoryByTicketCategoryID(ticketCategoryID);
        int numberOfTicketsAvailable = ticketCategory.getNoAvailable();
        ticketCategory.setNoAvailable(numberOfTicketsAvailable-numberOfTickets);
        Order order = new Order(ticketCategory,customer,numberOfTickets,LocalDateTime.now(),
                numberOfTickets*ticketCategory.getPrice());

        return orderRepository.save(order);
    }
}
