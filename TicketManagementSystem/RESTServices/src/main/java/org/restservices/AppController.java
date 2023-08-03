package org.restservices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.business.ITicketManagementService;
import org.model.Event;
import org.model.Order;
import org.model.TicketCategory;
import org.model.Venue;
import org.model.dtos.*;
import org.model.errors.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/management")
public class AppController {

    private static final String template = "Hello, %s!";

    private static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping("/greeting")
    public  String greeting() {
        return String.format(template, "Alex");
    }

    @Autowired
    ITicketManagementService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        try {
            // Authenticate the user using the provided credentials (email and password)
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password())
            );
            // If authentication is successful, load the user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.username());
            // You can directly return the UserDetails as a response
            return ResponseEntity.ok(userDetails);
        } catch (BadCredentialsException e) {
            // If authentication fails (incorrect username or password), return an error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
        }
    }

    @RequestMapping(value = "/logout", method=RequestMethod.GET)
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResponseEntity.ok("Logout Successful");
    }

    @RequestMapping(value = "/events/all", method=RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public Event[] getAllEvents() {
        List<Event> events = service.findAllEvents();
        LOGGER.info(events);
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
            VenueDTO venueDTO = new VenueDTO(venue.getLocation(), venue.getCapacity(), venue.getType());

            EventDTO eventDTO = new EventDTO(event.getEventID(),venueDTO,event.getEventType().getName(),event.getName(),
                    event.getDescription(),event.getStartDate(),event.getEndDate(),ticketCategoryDTOS,event.getImage());

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
    public OrderDTO[] getOrdersByCustomerEmail() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<Order> orders = service.findAllOrdersForCustomer(email);
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        int eventID = orderRequest.eventID();
        int ticketCategoryID = orderRequest.ticketCategoryID();
        int numberOfTickets = orderRequest.numberOfTickets();

        Optional<Order> orderOptional = service.saveOrder(email, ticketCategoryID, numberOfTickets);

        if (orderOptional.isEmpty()) {
            Error error = new Error("Order could not be added, customer or ticket category not found");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } else {
            Order order = orderOptional.get();
            TicketCategoryDTO ticketCategoryDTO = getTicketCategoryDTOFromTicketCategory(order.getTicketCategory());
            OrderDTO orderDTO = new OrderDTO(order.getOrderID(), eventID, ticketCategoryDTO, order.getOrderedAt(), order.getNumberOfTickets(), order.getTotalPrice());
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        }


    }

}
