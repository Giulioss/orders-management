package it.ultraistinct.ordersmanagement.domain.client.service;

import it.ultraistinct.ordersmanagement.domain.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
}
