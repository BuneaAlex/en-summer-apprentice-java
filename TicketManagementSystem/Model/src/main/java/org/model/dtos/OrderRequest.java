package org.model.dtos;

public record OrderRequest(int customerID, int eventID, int ticketCategoryID, int numberOfTickets) {
}
