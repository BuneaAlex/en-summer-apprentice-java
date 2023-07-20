package org.business;

import org.model.Event;
import org.model.Order;
import org.model.TicketCategory;

import java.util.List;

public interface ITicketManagementService {

    List<Event> findAllEvents();

    List<Order> findAllOrdersForCustomer(int customerID);
    List<Event> findEventsByVenueLocationAndByEventType(String location,String eventType);

    List<TicketCategory> findTicketCategoriesByEvent(Event event);

    Order saveOrder(int customerID,int ticketCategoryID,int numberOfTickets);
}
