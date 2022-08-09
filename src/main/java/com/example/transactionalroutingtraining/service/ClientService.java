package com.example.transactionalroutingtraining.service;

import com.example.transactionalroutingtraining.controller.ClientRequest;
import com.example.transactionalroutingtraining.dto.ClientDto;
import com.example.transactionalroutingtraining.entity.Client;
import com.example.transactionalroutingtraining.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public List<ClientDto> getClients() {
        List<ClientDto> clientDtoList = new ArrayList<>();
        clientRepository.findAll().forEach(client -> clientDtoList.add(ClientDto.toDto(client)));
        return clientDtoList;
    }
    
    public ClientDto getClientById(Long id) {
        return ClientDto.toDto(clientRepository.findClientById(id));
    }

    public void removeClientById(Long id) {
       clientRepository.deleteClientById(id);
    }
    
    @Transactional
    public void addClient(ClientRequest clientRequest) {
        Client client = Client.builder()
                .name(clientRequest.getName())
                .deleted(clientRequest.isDeleted())
                .nationalId(clientRequest.getNationalId())
                .build();
        clientRepository.save(client);
    }

}
