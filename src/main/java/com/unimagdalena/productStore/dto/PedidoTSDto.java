package com.unimagdalena.productStore.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.unimagdalena.productStore.enums.pedido.PedidoStatus;

public record PedidoTSDto(
        ClienteDto cliente,
        PedidoStatus status,
        DetalleEnvioDto detalleEnvio,
        PagoDto pago,
        List<ItemPedidoDto> itemsPedido) {
}
