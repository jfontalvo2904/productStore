package com.unimagdalena.productStore.dto.itemPedido;

public record ItemPedidoDto(Long id,
                            Long pedido_id,
                            Long producto_id,
                            Integer cantidad,
                            Float precioUnitario) {
}
