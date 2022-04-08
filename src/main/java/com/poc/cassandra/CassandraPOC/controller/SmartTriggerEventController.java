package com.poc.cassandra.CassandraPOC.controller;

import com.poc.cassandra.CassandraPOC.model.SmartTriggerEvent;
import com.poc.cassandra.CassandraPOC.model.SmartTriggerEventKey;
import com.poc.cassandra.CassandraPOC.repo.SmartTriggerEventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Dante Basso <dantebasso@gmail.com>
 * @since 05-04-2022
 */

@Slf4j
@RestController
@RequestMapping("/api/smt")
public class SmartTriggerEventController {

    @Autowired
    SmartTriggerEventRepository repository;

    @PostMapping("")
    public ResponseEntity<SmartTriggerEvent> createBook(@RequestBody SmartTriggerEvent smt) {
        try {
            final SmartTriggerEvent saved = this.repository.insertWithTtl(smt, Duration.ofSeconds(60));
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<SmartTriggerEvent>> getAllBooks() {
        final List<SmartTriggerEvent> list = repository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{partnerId}/{merchantId}/{timestamp}/{deviceId}")
    public ResponseEntity<SmartTriggerEvent> findOne(@PathVariable("partnerId") final int partnerId, @PathVariable("merchantId") final int merchantId, @PathVariable("timestamp") final Instant timestamp, @PathVariable("deviceId") final UUID deviceId) {
        final SmartTriggerEventKey id = new SmartTriggerEventKey();
        id.setMerchantId(merchantId);
        id.setPartnerId(partnerId);
        id.setEventTimestamp(timestamp);
        id.setDeviceId(deviceId);
        final Optional<SmartTriggerEvent> optional = repository.findById(id);
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{partnerId}/{merchantId}")
    public ResponseEntity<List<SmartTriggerEvent>> findAllDataByPartnerAndMerchant(@PathVariable("partnerId") final int partnerId, @PathVariable("merchantId") final int merchantId) {
        final SmartTriggerEventKey key = new SmartTriggerEventKey();
        key.setMerchantId(merchantId);
        key.setPartnerId(partnerId);
        final List<SmartTriggerEvent> list = repository.findList(key.getPartnerId(), key.getMerchantId());
        return ResponseEntity.ok(list);
    }

    @GetMapping("afterDays/{partnerId}/{merchantId}/{days}")
    public ResponseEntity<List<SmartTriggerEvent>> getAfter(@PathVariable("partnerId") final int partnerId, @PathVariable("merchantId") final int merchantId, @PathVariable("days") final int days) {
        final List<SmartTriggerEvent> list = repository.findAfter(partnerId, merchantId, Instant.now().minus(days, ChronoUnit.DAYS));
        return ResponseEntity.ok(list);
    }



}
