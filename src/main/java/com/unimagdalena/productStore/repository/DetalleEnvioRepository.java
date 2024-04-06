package com.unimagdalena.productStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unimagdalena.productStore.entity.DetalleEnvio;


public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, Long> {
}
