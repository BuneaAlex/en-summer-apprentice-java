package org.model.dtos;

import java.io.Serializable;

public record VenueDTO(String type,
                       int capacity,
                       String location) implements Serializable {

}
