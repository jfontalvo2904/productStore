package com.unimagdalena.productStore.dto.pedido;

import com.unimagdalena.productStore.enums.pedido.PedidoStatus;

public record PedidoToUpdateDto(Long cliente_id, PedidoStatus status) {}
