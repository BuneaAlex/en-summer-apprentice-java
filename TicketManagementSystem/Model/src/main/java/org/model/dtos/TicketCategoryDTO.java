package org.model.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TicketCategoryDTO implements Serializable {

    private int id;
    private String description;
    private float price;

    public TicketCategoryDTO(int id, String description, float price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public TicketCategoryDTO() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketCategoryDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
