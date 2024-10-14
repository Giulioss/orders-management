package it.ultraistinct.ordersmanagement.database.repository;

import it.ultraistinct.ordersmanagement.database.entity.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<Client, Long> {
}
