package org.persistence;

import org.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {

    List<Event> findByVenueTypeAndEventType_Name(String venueType, String typeName);
}
