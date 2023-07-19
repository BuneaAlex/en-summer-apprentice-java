package org.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Events")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventID")
    private int eventID;

    @ManyToOne
    @JoinColumn(name = "venueID", referencedColumnName = "venueID")
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "eventTypeID", referencedColumnName = "eventTypeID")
    private EventType eventType;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Column(name = "image")
    private String image;

    public Event(int id, Venue venue, EventType eventType, String name, String description, LocalDateTime startDate, LocalDateTime endDate, String image) {
        this.eventID = id;
        this.venue = venue;
        this.eventType = eventType;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
    }

    public Event(Venue venue, EventType eventType, String name, String description, LocalDateTime startDate, LocalDateTime endDate, String image) {
        this.venue = venue;
        this.eventType = eventType;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
    }

    public Event() {
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

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventID == event.eventID && Objects.equals(venue, event.venue) && Objects.equals(eventType, event.eventType) && Objects.equals(name, event.name) && Objects.equals(description, event.description) && Objects.equals(startDate, event.startDate) && Objects.equals(endDate, event.endDate) && Objects.equals(image, event.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventID, venue, eventType, name, description, startDate, endDate, image);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + eventID +
                ", venueID=" + venue +
                ", eventTypeID=" + eventType +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", image='" + image + '\'' +
                '}';
    }
}
