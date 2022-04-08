package com.poc.cassandra.CassandraPOC.repo.impl;

import com.poc.cassandra.CassandraPOC.repo.ExtendedCassandraRepository;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import java.time.Duration;

/**
 * @author Dante Basso <dcbasso@gmail.com>
 * @since 05-04-2022
 */

public class ExtendedCassandraRepositoryImpl<T, ID> extends SimpleCassandraRepository<T, ID> implements ExtendedCassandraRepository<T, ID> {

    private final CassandraOperations smtOperations;

    public ExtendedCassandraRepositoryImpl(CassandraEntityInformation<T, ID> metadata, CassandraOperations operations) {
        super(metadata, operations);
        this.smtOperations = operations;
    }

    @Override
    public T insertWithTtl(T obj, Duration ttl) {
        return this.smtOperations.insert(obj, InsertOptions.builder().ttl(ttl).build()).getEntity();
    }

}
