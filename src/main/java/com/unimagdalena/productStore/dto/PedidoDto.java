package com.unimagdalena.productStore.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.unimagdalena.productStore.enums.pedido.PedidoStatus;

public record PedidoDto(Long id,
        ClienteDto cliente,
        LocalDateTime fechaPedido,
        PedidoStatus status,
        DetalleEnvioDto detalleEnvio,
        PagoDto pago,
        List<ItemPedidoDto> itemsPedido) {
}