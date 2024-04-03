package com.unimagdalena.productStore.services;

import java.util.List;

import com.unimagdalena.productStore.dto.ItemPedidoDto;
import com.unimagdalena.productStore.exceptions.ItemPedidoNotFoundException;

public interface ItemPedidoServices {

    List<ItemPedidoDto> getAll();

    ItemPedidoDto guardar(ItemPedidoDto data);

    ItemPedidoDto actualizar(Long id, ItemPedidoDto data);

    ItemPedidoDto buscarPorId(Long id) throws ItemPedidoNotFoundException;

    void eliminarPorId(Long id);
}
