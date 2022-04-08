package com.poc.cassandra.CassandraPOC.repo;

import org.springframework.data.cassandra.core.EntityWriteResult;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.Duration;

/**
 * @author Dante Basso <dcbasso@gmail.com>
 * @since 05-04-2022
 */

@NoRepositoryBean
public interface ExtendedCassandraRepository<T, ID> extends CassandraRepository<T, ID> {

    T insertWithTtl(T obj, Duration ttl);

}
