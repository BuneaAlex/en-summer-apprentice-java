package org.model.dtos;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.model.Venue;
import org.model.dtos.TicketCategoryDTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO implements Serializable {

    private int eventID;

    private VenueDTO venue;

    private String eventType;

    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private List<TicketCategoryDTO> ticketCategories;

    private String image;
}
