package org.model.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record EventDTO(int eventID,
                       VenueDTO venue,
                       String eventType,
                       String name,
                       String description,
                       String startDate,
                       String endDate,
                       List<TicketCategoryDTO> ticketCategories,
                       String image) implements Serializable {
}
