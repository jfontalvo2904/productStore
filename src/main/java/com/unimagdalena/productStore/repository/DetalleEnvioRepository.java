package com.unimagdalena.productStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unimagdalena.productStore.entity.DetalleEnvio;
import com.unimagdalena.productStore.entity.Pedido;

public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, Long> {
    List<DetalleEnvio> findByPedido(Pedido pedido);
}
