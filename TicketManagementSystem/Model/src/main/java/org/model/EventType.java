package org.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "EventTypes")
public class EventType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventTypeID")
    private int eventTypeID;

    @Column(name = "name")
    private String name;

    public EventType() {
    }

    public EventType(int id, String name) {
        this.eventTypeID = id;
        this.name = name;
    }

    public EventType(String name) {
        this.name = name;
    }

    public int getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(int eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventType eventType = (EventType) o;
        return eventTypeID == eventType.eventTypeID && Objects.equals(name, eventType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventTypeID, name);
    }

    @Override
    public String toString() {
        return "EventType{" +
                "id=" + eventTypeID +
                ", name='" + name + '\'' +
                '}';
    }
}
