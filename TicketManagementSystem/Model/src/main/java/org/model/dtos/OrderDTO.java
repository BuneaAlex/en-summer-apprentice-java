package org.model.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderDTO implements Serializable {

    private int orderID;
    private int eventID;
    private TicketCategoryDTO ticketCategory;
    private LocalDateTime orderedAt;
    private int numberOfTickets;
    private float totalPrice;

    public OrderDTO(int orderID, int eventID, TicketCategoryDTO ticketCategory, LocalDateTime orderedAt, int numberOfTickets, float totalPrice) {
        this.orderID = orderID;
        this.eventID = eventID;
        this.ticketCategory = ticketCategory;
        this.orderedAt = orderedAt;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = totalPrice;
    }

    public OrderDTO() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public TicketCategoryDTO getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategoryDTO ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
