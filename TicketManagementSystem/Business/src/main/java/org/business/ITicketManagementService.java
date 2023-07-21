package org.business;

import org.model.Event;
import org.model.Order;
import org.model.TicketCategory;

import java.util.List;
import java.util.Optional;

public interface ITicketManagementService {

    List<Event> findAllEvents();

    List<Order> findAllOrdersForCustomer(int customerID);
    List<Event> findEventsByVenueTypeAndByEventType(String venueType,String eventType);

    List<TicketCategory> findTicketCategoriesByEvent(Event event);

    Optional<Order> saveOrder(int customerID, int ticketCategoryID, int numberOfTickets);

    List<Event> findEventsByVenueType(String venueType);
    List<Event> findEventByEventType(String eventType);
}
