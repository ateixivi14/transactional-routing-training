package com.example.transactionalroutingtraining.repository;


import com.example.transactionalroutingtraining.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    List<Client> findAll();
    
    Client findClientById(Long id);

    @Modifying
    void deleteClientById(Long id);

}
