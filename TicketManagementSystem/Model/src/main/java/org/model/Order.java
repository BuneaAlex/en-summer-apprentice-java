package org.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private int orderID;

    @ManyToOne
    @JoinColumn(name = "ticketCategoryID", referencedColumnName = "ticketCategoryID")
    private TicketCategory ticketCategory;

    @ManyToOne
    @JoinColumn(name = "customerID",referencedColumnName = "customerID")
    private Customer customer;

    @Column(name = "numberOfTickets")
    private int numberOfTickets;

    @Column(name = "orderedAt")
    private LocalDateTime orderedAt;

    @Column(name = "totalPrice")
    private float totalPrice;


    public Order() {
    }

    public Order(TicketCategory ticketCategory, Customer customer, int numberOfTickets, LocalDateTime orderedAt, float totalPrice) {
        this.ticketCategory = ticketCategory;
        this.customer = customer;
        this.numberOfTickets = numberOfTickets;
        this.orderedAt = orderedAt;
        this.totalPrice = totalPrice;
    }

    public Order(int id, TicketCategory ticketCategory, Customer customer, int numberOfTickets, LocalDateTime orderedAt, float totalPrice) {
        this.orderID = id;
        this.ticketCategory = ticketCategory;
        this.customer = customer;
        this.numberOfTickets = numberOfTickets;
        this.orderedAt = orderedAt;
        this.totalPrice = totalPrice;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderID == order.orderID && numberOfTickets == order.numberOfTickets && Float.compare(order.totalPrice, totalPrice) == 0 && Objects.equals(ticketCategory, order.ticketCategory) && Objects.equals(customer, order.customer) && Objects.equals(orderedAt, order.orderedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, ticketCategory, customer, numberOfTickets, orderedAt, totalPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + orderID +
                ", ticketCategory=" + ticketCategory +
                ", customer=" + customer +
                ", numberOfTickets=" + numberOfTickets +
                ", orderedAt=" + orderedAt +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
