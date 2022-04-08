package com.poc.cassandra.CassandraPOC;

import com.poc.cassandra.CassandraPOC.repo.impl.ExtendedCassandraRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories(repositoryBaseClass = ExtendedCassandraRepositoryImpl.class)
public class CassandraPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandraPocApplication.class, args);
	}

}
