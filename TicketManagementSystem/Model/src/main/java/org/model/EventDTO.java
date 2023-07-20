package org.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class EventDTO implements Serializable {

    private int eventID;

    private Venue venue;

    private String eventType;

    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private List<TicketCategoryDTO> ticketCategories;

    private String image;

    public EventDTO(int eventID, Venue venue, String eventType, String name, String description, LocalDateTime startDate, LocalDateTime endDate, List<TicketCategoryDTO> ticketCategories, String image) {
        this.eventID = eventID;
        this.venue = venue;
        this.eventType = eventType;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketCategories = ticketCategories;
        this.image = image;
    }

    public EventDTO() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<TicketCategoryDTO> getTicketCategories() {
        return ticketCategories;
    }

    public void setTicketCategories(List<TicketCategoryDTO> ticketCategories) {
        this.ticketCategories = ticketCategories;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "eventID=" + eventID +
                ", venue=" + venue +
                ", eventType='" + eventType + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", ticketCategories=" + ticketCategories +
                '}';
    }
}
