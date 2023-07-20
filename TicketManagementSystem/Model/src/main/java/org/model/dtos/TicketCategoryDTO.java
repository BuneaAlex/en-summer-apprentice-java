package org.model.dtos;

import java.io.Serializable;

public record TicketCategoryDTO(int id,
                                String description,
                                float price) implements Serializable {
}
