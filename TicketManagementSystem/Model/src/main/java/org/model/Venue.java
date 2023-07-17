package org.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="Venues")
public class Venue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venueID")
    private int venueID;

    @Column(name = "type")
    private String type;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "location")
    private String location;

    public Venue() {
    }

    public Venue(int id, String type, int capacity, String location) {
        this.venueID = id;
        this.type = type;
        this.capacity = capacity;
        this.location = location;
    }

    public Venue(String type, int capacity, String location) {
        this.type = type;
        this.capacity = capacity;
        this.location = location;
    }

    public int getVenueID() {
        return venueID;
    }

    public void setVenueID(int venueID) {
        this.venueID = venueID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venue venue = (Venue) o;
        return venueID == venue.venueID && capacity == venue.capacity && Objects.equals(type, venue.type) && Objects.equals(location, venue.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venueID, type, capacity, location);
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + venueID +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", location='" + location + '\'' +
                '}';
    }
}
