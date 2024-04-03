package com.unimagdalena.productStore.repository;

import com.unimagdalena.productStore.enums.pago.MetodoDePago;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unimagdalena.productStore.entity.Pago;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago,Long> {

    @Query("SELECT p FROM Pago p WHERE p.fechaPago BETWEEN ?1 AND ?2")
    List<Pago> recuperarPagosEnRangoDeFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    @Query("SELECT p FROM Pago p WHERE p.pedido.id = ?1 AND p.metodoDePago = ?2")
    List<Pago> recuperarPagosPorPedidoYMetodoDePago(Long pedidoId, MetodoDePago metodoDePago);
    
}
