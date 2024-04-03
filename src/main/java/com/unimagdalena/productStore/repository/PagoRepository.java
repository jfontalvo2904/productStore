package com.unimagdalena.productStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unimagdalena.productStore.entity.Pago;

public interface PagoRepository extends JpaRepository<Pago,Long> {
    
}
