package com.unimagdalena.productStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unimagdalena.productStore.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
