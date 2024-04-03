package com.unimagdalena.productStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unimagdalena.productStore.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.email = ?1")
    Cliente buscarClientePorEmail(String email);
    
}
