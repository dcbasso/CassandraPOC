package com.poc.cassandra.CassandraPOC.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

/**
 * @author Dante Basso <dcbasso@gmail.com>
 * @since 05-04-2022
 */
@Table(value = "smartrigger_events")
public class SmartTriggerEvent {

    @PrimaryKey
    private SmartTriggerEventKey id;

    @Column("engagement_id")
    private UUID engagementId;

    @Column("event_type")
    private EventType eventType;

    @Column("event_data")
    private String eventData;

    public SmartTriggerEventKey getId() {
        return id;
    }

    public void setId(SmartTriggerEventKey id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public UUID getEngagementId() {
        return engagementId;
    }

    public void setEngagementId(UUID engagementId) {
        this.engagementId = engagementId;
    }

}
