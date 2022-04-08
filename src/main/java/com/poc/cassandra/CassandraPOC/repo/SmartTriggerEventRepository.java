package com.poc.cassandra.CassandraPOC.repo;

import com.poc.cassandra.CassandraPOC.model.SmartTriggerEvent;
import com.poc.cassandra.CassandraPOC.model.SmartTriggerEventKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * @author Dante Basso <dcbasso@gmail.com>
 * @since 05-04-2022
 */
@Repository
public interface SmartTriggerEventRepository extends ExtendedCassandraRepository<SmartTriggerEvent, SmartTriggerEventKey> {

    @Query("SELECT * FROM smartrigger_events WHERE partner_id = :partnerId AND merchant_id = :merchantId")
    List<SmartTriggerEvent> findList(int partnerId, int merchantId) ;

    @Query("SELECT * FROM smartrigger_events WHERE partner_id = :partnerId AND merchant_id = :merchantId AND event_timestamp >= :after")
    List<SmartTriggerEvent> findAfter(int partnerId, int merchantId, Instant after) ;

}
