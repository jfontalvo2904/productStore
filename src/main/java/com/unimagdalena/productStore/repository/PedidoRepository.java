package com.unimagdalena.productStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unimagdalena.productStore.entity.Cliente;
import com.unimagdalena.productStore.entity.Pedido;
import com.unimagdalena.productStore.enums.pedido.PedidoStatus;

import java.time.LocalDateTime;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
