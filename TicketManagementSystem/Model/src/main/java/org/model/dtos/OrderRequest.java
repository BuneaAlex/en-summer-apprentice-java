package org.model.dtos;

public record OrderRequest(int eventID, int ticketCategoryID, int numberOfTickets) {
}
