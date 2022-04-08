package com.poc.cassandra.CassandraPOC.controller;

import com.poc.cassandra.CassandraPOC.model.SmartTriggerEvent;
import com.poc.cassandra.CassandraPOC.model.SmartTriggerEventKey;
import com.poc.cassandra.CassandraPOC.repo.SmartTriggerEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Optional;

/**
 * @author Dante Basso <dantebasso@rezolve.com>
 * @since 05-04-2022
 */
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
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<SmartTriggerEvent>> getAllBooks() {
        final List<SmartTriggerEvent> list = repository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{partnerId}/{merchantId}")
    public ResponseEntity<SmartTriggerEvent> getAllById(@PathVariable("partnerId") final int partnerId, @PathVariable("merchantId") final int merchantId) {
        final SmartTriggerEventKey key = new SmartTriggerEventKey();
        key.setMerchantId(merchantId);
        key.setPartnerId(partnerId);
        final Optional<SmartTriggerEvent> optionalBook = repository.findById(key);
        return optionalBook.map(smt -> new ResponseEntity<>(smt, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("after/{partnerId}/{merchantId}")
    public ResponseEntity<List<SmartTriggerEvent>> getAfter(@PathVariable("partnerId") final int partnerId, @PathVariable("merchantId") final int merchantId) {
        final SmartTriggerEventKey key = new SmartTriggerEventKey();
        key.setMerchantId(merchantId);
        key.setPartnerId(partnerId);

        final List<SmartTriggerEvent> list = repository.test(partnerId, merchantId, Instant.now().minus(4, ChronoUnit.HOURS));
//        final List<SmartTriggerEvent> list = repository.findByIdPartnerIdAndIdMerchantIdAndEventTimestampAfter(partnerId, merchantId, Instant.now().minus(4, ChronoUnit.HOURS));
        return ResponseEntity.ok(list);
    }



}
