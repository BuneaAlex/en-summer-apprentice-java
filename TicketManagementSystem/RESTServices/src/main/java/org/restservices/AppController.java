package org.restservices;

import org.business.ITicketManagementService;
import org.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    @RequestMapping(value = "/events/all", method=RequestMethod.GET)
    public Event[] getAllEvents() {
        List<Event> events = service.findAllEvents();
        return events.toArray(new Event[0]);
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public EventDTO[] getEventsByVenueLocationAndEventType(@RequestParam("venueType") String venueType,
                                                        @RequestParam("eventType") String eventType) {

        List<Event> events = service.findEventsByVenueLocationAndByEventType(venueType,eventType);
        List<EventDTO> eventDTOS = getEventDTOS(events);
        return eventDTOS.toArray(new EventDTO[0]);
    }

    private List<EventDTO> getEventDTOS(List<Event> events) {
        List<EventDTO> eventDTOS = new ArrayList<>();
        for(Event event : events)
        {
            List<TicketCategory> ticketCategories = service.findTicketCategoriesByEvent(event);
            List<TicketCategoryDTO> ticketCategoryDTOS = getTicketCategoryDTOS(ticketCategories);

            EventDTO eventDTO = new EventDTO(event.getEventID(),event.getVenue(),event.getEventType().getName(),
                    event.getName(),event.getDescription(),event.getStartDate(),event.getEndDate(),ticketCategoryDTOS, event.getImage());
            eventDTOS.add(eventDTO);
        }
        return eventDTOS;
    }

    private TicketCategoryDTO getTicketCategoryDTOFromTicketCategory(TicketCategory ticketCategory)
    {
        return new TicketCategoryDTO(ticketCategory.getTicketCategoryID(),ticketCategory.getDescription(),ticketCategory.getPrice());
    }

    private List<TicketCategoryDTO> getTicketCategoryDTOS(List<TicketCategory> ticketCategories) {
        List<TicketCategoryDTO> ticketCategoryDTOS = new ArrayList<>();
        for(TicketCategory ticket : ticketCategories)
        {
            TicketCategoryDTO ticketCategoryDTO = getTicketCategoryDTOFromTicketCategory(ticket);
            ticketCategoryDTOS.add(ticketCategoryDTO);
        }
        return ticketCategoryDTOS;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public OrderDTO[] getOrdersByCustomerId(@RequestParam("customerId") int customerId) {

        List<Order> orders = service.findAllOrdersForCustomer(customerId);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for(Order order : orders)
        {
            TicketCategoryDTO ticketCategoryDTO = getTicketCategoryDTOFromTicketCategory(order.getTicketCategory());
            OrderDTO orderDTO = new OrderDTO(order.getOrderID(), order.getTicketCategory().getEvent().getEventID(),ticketCategoryDTO,
                    order.getOrderedAt(),order.getNumberOfTickets(),order.getTotalPrice());

            orderDTOS.add(orderDTO);

        }
        return orderDTOS.toArray(new OrderDTO[0]);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public OrderDTO saveOrder(@RequestBody OrderRequest orderRequest) {

        int customerID = orderRequest.getCustomerID();
        int eventID = orderRequest.getEventID();
        int ticketCategoryID = orderRequest.getTicketCategoryID();
        int numberOfTickets = orderRequest.getNumberOfTickets();

        Order order = service.saveOrder(customerID,ticketCategoryID,numberOfTickets);
        TicketCategoryDTO ticketCategoryDTO = getTicketCategoryDTOFromTicketCategory(order.getTicketCategory());
        OrderDTO orderDTO = new OrderDTO(order.getOrderID(),eventID,ticketCategoryDTO,order.getOrderedAt(),order.getNumberOfTickets(),order.getTotalPrice());
        return orderDTO;
    }

}
