package it.ultraistinct.ordersmanagement.database.service;

import it.ultraistinct.ordersmanagement.database.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
}
