package org.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TicketCategories")
public class TicketCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketCategoryID")
    private int ticketCategoryID;

    @ManyToOne
    @JoinColumn(name = "eventID", referencedColumnName = "eventID")
    private Event event;

    @Column(name = "price")
    private float price;

    @Column(name = "noAvailable")
    private int noAvailable;

    @Column(name = "description")
    private String description;

    public TicketCategory(int id, Event event, float price, int noAvailable, String description) {
        this.ticketCategoryID = id;
        this.event = event;
        this.price = price;
        this.noAvailable = noAvailable;
        this.description = description;
    }

    public TicketCategory(Event event, float price, int noAvailable, String description) {
        this.event = event;
        this.price = price;
        this.noAvailable = noAvailable;
        this.description = description;
    }

    public TicketCategory() {
    }

    public int getTicketCategoryID() {
        return ticketCategoryID;
    }

    public void setTicketCategoryID(int ticketCategoryID) {
        this.ticketCategoryID = ticketCategoryID;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNoAvailable() {
        return noAvailable;
    }

    public void setNoAvailable(int noAvailable) {
        this.noAvailable = noAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCategory that = (TicketCategory) o;
        return ticketCategoryID == that.ticketCategoryID && Float.compare(that.price, price) == 0 && noAvailable == that.noAvailable && Objects.equals(event, that.event) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketCategoryID, event, price, noAvailable, description);
    }

    @Override
    public String toString() {
        return "TicketCategory{" +
                "ticketCategoryID=" + ticketCategoryID +
                ", event=" + event +
                ", price=" + price +
                ", noAvailable=" + noAvailable +
                ", description='" + description + '\'' +
                '}';
    }
}
