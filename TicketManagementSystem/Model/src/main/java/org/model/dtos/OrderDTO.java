package org.model.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

public record OrderDTO(int orderID,
                       int eventID,
                       TicketCategoryDTO ticketCategory,
                       String orderedAt,
                       int numberOfTickets,
                       float totalPrice) implements Serializable {
}
