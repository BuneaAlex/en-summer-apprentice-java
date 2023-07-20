package org.restservices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.business.ITicketManagementService;
import org.model.*;
import org.model.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin
@RestController
@RequestMapping("/management")
public class AppController {

    private static final String template = "Hello, %s!";

    private static final Logger log= LogManager.getLogger();

    @RequestMapping("/greeting")
    public  String greeting() {
        return String.format(template, "Alex");
    }

    @Autowired
    ITicketManagementService service;


    @RequestMapping(value = "/events/all", method=RequestMethod.GET)
    public Event[] getAllEvents() {
        List<Event> events = service.findAllEvents();
        log.info(events);
        return events.toArray(new Event[0]);
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public EventDTO[] getEventsByVenueLocationAndEventType(@RequestParam(value = "venueType",required = false) String venueType,
                                                           @RequestParam(value = "eventType",required = false) String eventType) {

        List<Event> events = new ArrayList<>();
        if(venueType != null && eventType != null)
             events = service.findEventsByVenueTypeAndByEventType(venueType,eventType);
        else if (venueType != null) {
            events = service.findEventsByVenueType(venueType);
        }
        else if (eventType != null){
            events = service.findEventByEventType(eventType);
        }
        else
        {
            events = service.findAllEvents();
        }


        List<EventDTO> eventDTOS = getEventDTOS(events);
        return eventDTOS.toArray(new EventDTO[0]);
    }

    private List<EventDTO> getEventDTOS(List<Event> events) {
        List<EventDTO> eventDTOS = new ArrayList<>();
        for(Event event : events)
        {
            List<TicketCategory> ticketCategories = service.findTicketCategoriesByEvent(event);
            List<TicketCategoryDTO> ticketCategoryDTOS = getTicketCategoryDTOS(ticketCategories);
            Venue venue = event.getVenue();
            VenueDTO venueDTO = VenueDTO.builder().
                    location(venue.getLocation()).
                    capacity(venue.getCapacity()).
                    type(venue.getType()).build();


            EventDTO eventDTO = EventDTO.builder()
                    .eventID(event.getEventID())
                    .venue(venueDTO)
                    .eventType(event.getEventType().getName())
                    .name(event.getName())
                    .description(event.getDescription())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
                    .ticketCategories(ticketCategoryDTOS)
                    .image(event.getImage())
                    .build();
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
    public ResponseEntity<?> saveOrder(@RequestBody OrderRequest orderRequest) {

        int customerID = orderRequest.customerID();
        int eventID = orderRequest.eventID();
        int ticketCategoryID = orderRequest.ticketCategoryID();
        int numberOfTickets = orderRequest.numberOfTickets();

        Optional<Order> orderOptional = service.saveOrder(customerID, ticketCategoryID, numberOfTickets);

        if (orderOptional.isEmpty()) {
            return new ResponseEntity<>("Order could not be added, customer or ticket category not found", HttpStatus.NOT_FOUND);
        } else {
            Order order = orderOptional.get();
            TicketCategoryDTO ticketCategoryDTO = getTicketCategoryDTOFromTicketCategory(order.getTicketCategory());
            OrderDTO orderDTO = new OrderDTO(order.getOrderID(), eventID, ticketCategoryDTO, order.getOrderedAt(), order.getNumberOfTickets(), order.getTotalPrice());
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        }


    }

}
