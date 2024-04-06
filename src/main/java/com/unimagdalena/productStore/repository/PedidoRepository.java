package com.unimagdalena.productStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unimagdalena.productStore.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
