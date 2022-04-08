## Simple POC / Cassandra


### CREATE TABLE

```
CREATE TABLE smartrigger_events (
    partner_id         INT,
    merchant_id        INT,
    engagement_id      UUID,
    device_id          UUID,
    event_timestamp    TIMESTAMP,
    event_type         VARCHAR,
    event_data         TEXT,
    PRIMARY KEY(
        (partner_id, merchant_id),
    event_timestamp,
    device_id      
    )
)
WITH CLUSTERING ORDER BY (event_timestamp ASC, device_id ASC);
```