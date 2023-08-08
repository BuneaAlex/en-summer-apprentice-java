package org.business;

import org.model.Customer;
import org.model.Event;
import org.model.Order;
import org.model.TicketCategory;

import java.util.List;
import java.util.Optional;

public interface ITicketManagementService {

    List<Event> findAllEvents();

    List<Order> findAllOrdersForCustomer(String email);
    List<Event> findEventsByVenueTypeAndByEventType(String venueType,String eventType);

    List<TicketCategory> findTicketCategoriesByEvent(Event event);

    Optional<Order> saveOrder(String email, int ticketCategoryID, int numberOfTickets);

    List<Event> findEventsByVenueType(String venueType);
    List<Event> findEventByEventType(String eventType);

    Customer findCustomerByEmail(String email);

    Event findEventById(int eventId);
}
