package com.poc.cassandra.CassandraPOC.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

/**
 * @author Dante Basso <dantebasso@rezolve.com>
 * @since 07-04-2022
 */

@PrimaryKeyClass
public class SmartTriggerEventKey implements Serializable {

    @PrimaryKeyColumn(name = "partner_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int partnerId;

    @PrimaryKeyColumn(name = "merchant_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private int merchantId;

//    @PrimaryKeyColumn(name = "engagement_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
//    private UUID engagementId;
//
//    @PrimaryKeyColumn(name = "device_id", ordinal = 3, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
//    private UUID deviceId;

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }
//
//    public UUID getEngagementId() {
//        return engagementId;
//    }
//
//    public void setEngagementId(UUID engagementId) {
//        this.engagementId = engagementId;
//    }
//
//    public UUID getDeviceId() {
//        return deviceId;
//    }
//
//    public void setDeviceId(UUID deviceId) {
//        this.deviceId = deviceId;
//    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
