package com.unimagdalena.productStore.dto.pedido;

import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoDto;
import com.unimagdalena.productStore.enums.pedido.PedidoStatus;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto(Long id,
        Long cliente_id,
        LocalDateTime fechaPedido,
        PedidoStatus status,
        Long detalle_envio_id,
        Long pago_id,
        List<ItemPedidoDto> itemsPedido) {
}
