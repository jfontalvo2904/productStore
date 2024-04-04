package com.unimagdalena.productStore.dto.producto;

import com.unimagdalena.productStore.dto.itemPedido.ItemPedidoDto;

import java.util.List;

public record ProductoDto(Long id,
                          String nombre,
                          Float price,
                          Integer stock,
                          List<ItemPedidoDto> itemsPedido) {
}
