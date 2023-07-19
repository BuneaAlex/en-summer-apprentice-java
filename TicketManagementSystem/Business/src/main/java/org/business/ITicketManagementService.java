package org.business;

import org.model.Event;

import java.util.List;

public interface ITicketManagementService {

    List<Event> findAllEvents();
    //List<Event> findEventsByVenueLocationAndByEventType(String location)
}
