package com.unimagdalena.productStore.dto.cliente;

import com.unimagdalena.productStore.dto.pedido.PedidoDto;
import com.unimagdalena.productStore.entity.Pedido;

import java.util.List;

public record ClienteDto(Long id,
                         String nombre,
                         String email,
                         String direccion,
                         List<PedidoDto> pedidos) {
}
