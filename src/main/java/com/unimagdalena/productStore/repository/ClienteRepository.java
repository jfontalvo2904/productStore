package com.unimagdalena.productStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unimagdalena.productStore.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("S")
    Cliente buscarClientePorEmail(String email);
    
}
