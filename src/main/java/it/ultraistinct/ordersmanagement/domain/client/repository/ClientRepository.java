package it.ultraistinct.ordersmanagement.domain.client.repository;

import it.ultraistinct.ordersmanagement.domain.client.entity.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveCrudRepository<Client, Long> {
}
