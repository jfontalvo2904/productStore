package com.unimagdalena.productStore.dto;

public record ItemPedidoDto(Long id,
        Long pedido,
        Long producto,
        Integer cantidad,
        Integer precioUnitario) {
}
