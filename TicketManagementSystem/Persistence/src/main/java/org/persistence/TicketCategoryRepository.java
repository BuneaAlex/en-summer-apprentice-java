package org.persistence;

import org.model.Event;
import org.model.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory,Integer> {

    List<TicketCategory> findTicketCategoriesByEvent(Event event);
    TicketCategory findTicketCategoryByTicketCategoryID(int ticketCategoryID);
}
